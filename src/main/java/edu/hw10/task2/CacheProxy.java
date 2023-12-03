package edu.hw10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

public final class CacheProxy<T> {

    private CacheProxy() {
    }

    public static <T> T create(T object) {
        return (T) Proxy.newProxyInstance(
            object.getClass().getClassLoader(),
            object.getClass().getInterfaces(),
            new CacheInvocationHandler<>(object)
        );
    }

    @RequiredArgsConstructor
    private final static class CacheInvocationHandler<T> implements InvocationHandler {
        private final Map<Method, Map<List<Object>, Object>> cachedValues = new HashMap<>();

        private final T cachingObject;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Cache.class)) {
                Cache cache = method.getAnnotation(Cache.class);
                if (cache.persist()) {
                    return method.invoke(cachingObject, args);
                }
                return computeCache(method, args);
            }
            return method.invoke(cachingObject, args);
        }

        public Object computeCache(Method method, Object[] args)
            throws InvocationTargetException, IllegalAccessException {
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
    }

}
