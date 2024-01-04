package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static Constructor<?> getConstructorWithMaxParams(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        if (constructors.length == 0) {
            throw new IllegalArgumentException("Class " + clazz.getName() + " has no constructors");
        }
        Constructor<?> constructor = constructors[0];
        int paramCount = constructor.getParameterCount();
        for (Constructor<?> value : constructors) {
            if (value.getParameterCount() > paramCount) {
                constructor = value;
                paramCount = constructor.getParameterCount();
            }
        }
        return constructor;
    }

    public static Method getMethod(Class<?> clazz, String methodName) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new IllegalArgumentException("No method " + methodName + " in " + clazz.getName());
    }
}
