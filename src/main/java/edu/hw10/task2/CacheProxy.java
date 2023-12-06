package edu.hw10.task2;

import edu.hw10.task2.serializer.Serializer;
import edu.hw10.task2.serializer.Serializers;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public final class CacheProxy {

    private CacheProxy() {
    }

    public static <T> T create(T object, Map<Class<?>, Serializer<?>> serializers, Path persistPath) {
        return (T) Proxy.newProxyInstance(
            object.getClass().getClassLoader(),
            object.getClass().getInterfaces(),
            new CacheInvocationHandler<>(object, serializers, persistPath)
        );
    }

    public static <T> T create(T object, Path persistPath) {
        return create(object, Serializers.defaultSerializers(), persistPath);
    }

    private final static class CacheInvocationHandler<T> implements InvocationHandler {
        private final Map<Method, Map<List<Object>, Object>> cachedValues = new HashMap<>();
        private final T cachingObject;
        private final Map<Class<?>, Serializer<?>> serializers;
        private final Path persistPath;

        @SneakyThrows
        private CacheInvocationHandler(T cachingObject, Map<Class<?>, Serializer<?>> serializers, Path persistPath) {
            this.cachingObject = cachingObject;
            this.serializers = serializers;
            this.persistPath = persistPath;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Cache.class)) {
                Cache cache = method.getAnnotation(Cache.class);
                if (cache.persist()) {
                    return computePersistCache(method, args);
                }
                return computeCache(method, args);
            }
            return method.invoke(cachingObject, args);
        }

        public Object computeCache(Method method, Object[] args) throws Throwable {
            List<Object> argsList = Arrays.asList(args);
            if (!cachedValues.containsKey(method)) {
                cachedValues.put(method, new HashMap<>());
            }
            if (!cachedValues.get(method).containsKey(argsList)) {
                Object result = method.invoke(cachingObject, args);
                cachedValues.get(method).put(argsList, result);
                return result;
            }
            return cachedValues.get(method).get(argsList);
        }

        public Object computePersistCache(Method method, Object[] args) {
            Serializer<?> resultSerializer = serializers.get(method.getReturnType());
            if (resultSerializer == null) {
                throw new IllegalArgumentException("Unsupported for persist return type: " + method.getReturnType());
            }
            if (args.length == 0) {
                return computeNoArgsPersistCache(method, args, resultSerializer);
            } else {
                return computeManyArgsPersistCache(method, args, resultSerializer);
            }
        }

        @SneakyThrows
        public Object computeNoArgsPersistCache(Method method, Object[] args, Serializer<?> resultSerializer) {
            if (Files.exists(persistPath.resolve(method.getName()))) {
                return resultSerializer.deserialize(Files.readString(persistPath.resolve(getFileName(method))));
            } else {
                Object result = method.invoke(cachingObject, args);
                Files.writeString(persistPath.resolve(method.getName()), resultSerializer.serialize(result));
                return result;
            }
        }

        @SneakyThrows
        public Object computeManyArgsPersistCache(Method method, Object[] args, Serializer<?> resultSerializer) {
            StringBuilder serializedArgumentsBuilder = new StringBuilder();
            for (Object arg : args) {
                Serializer<?> argSerializer = serializers.get(arg.getClass());
                if (argSerializer == null) {
                    throw new IllegalArgumentException("Unsupported for persist argument type: " + arg.getClass());
                }
                serializedArgumentsBuilder.append(argSerializer.serialize(arg)).append(";");
            }
            String serializedArguments = serializedArgumentsBuilder.toString();
            String fileName = getFileName(method);
            if (Files.exists(persistPath.resolve(fileName))) {
                Properties properties = new Properties();
                properties.load(Files.newBufferedReader(persistPath.resolve(fileName)));
                if (properties.containsKey(serializedArguments)) {
                    return resultSerializer.deserialize(properties.getProperty(serializedArguments));
                }
                return computeAndSaveCache(method, args, serializedArguments, resultSerializer, properties);
            } else {
                Properties properties = new Properties();
                return computeAndSaveCache(method, args, serializedArguments, resultSerializer, properties);
            }
        }

        @SneakyThrows
        private Object computeAndSaveCache(
            Method method,
            Object[] args,
            String serializedArguments,
            Serializer<?> resultSerializer,
            Properties properties
        ) {
            Object result = method.invoke(cachingObject, args);
            properties.put(serializedArguments, resultSerializer.serialize(result));
            properties.store(Files.newBufferedWriter(persistPath.resolve(getFileName(method))), null);
            return result;
        }

        private String getFileName(Method method) {
            return method.getName() + Arrays.stream(method.getParameters()).map(
                p -> "_" + p.getType().getName()
            ).collect(Collectors.joining());
        }
    }
}
