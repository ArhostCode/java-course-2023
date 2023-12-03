package edu.hw10.task1;

import edu.hw10.task1.annotations.NotNull;
import edu.hw10.task1.generators.Generators;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class RandomObjectGenerator {

    private final Random random;
    private final Mode mode;
    private final Map<Class<?>, FieldGenerator<?>> generators;

    public RandomObjectGenerator(Random random, Mode mode) {
        this.random = random;
        this.generators = new HashMap<>(Generators.defaultGenerators());
        this.mode = mode;
    }

    public <T> T nextObject(Class<T> clazz) {
        Constructor<?> constructor = ReflectionUtils.getConstructorWithMaxParams(clazz);
        Object[] params = generateParams(constructor.getParameters());
        try {
            return (T) constructor.newInstance(params);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create an instance of " + clazz.getName(), e);
        }
    }

    public <T> T nextObject(Class<T> clazz, String factoryMethod) {
        Method method = ReflectionUtils.getMethod(clazz, factoryMethod);
        Object[] params = generateParams(method.getParameters());
        try {
            return (T) method.invoke(null, params);
        } catch (Exception e) {
            throw new IllegalStateException(
                "Failed to create an instance with method " + factoryMethod + " of " + clazz.getName(), e);
        }
    }

    private Object[] generateParams(Parameter[] parameters) {
        Object[] params = new Object[parameters.length];
        for (int i = 0; i < params.length; i++) {
            FieldGenerator<?> generator = generators.get(parameters[i].getType());
            if (generator == null) {
                if (mode == Mode.GENERATE_NESTED
                    || (mode == Mode.SKIP_NESTED_IF_CAN && parameters[i].isAnnotationPresent(NotNull.class))) {
                    params[i] = nextObject(parameters[i].getType());
                }
            } else {
                params[i] = generator.generate(random, parameters[i].getAnnotations());
            }
        }
        return params;
    }

    public enum Mode {
        SKIP_NESTED_ALWAYS,
        SKIP_NESTED_IF_CAN,
        GENERATE_NESTED
    }

}
