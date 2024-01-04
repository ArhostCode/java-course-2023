package edu.hw11.task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public final class HelloWorldCreator {

    private HelloWorldCreator() {
    }

    public static Object createHelloWorld() {
        try (var unloaded = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
        ) {
            return unloaded.load(HelloWorldCreator.class.getClassLoader()).getLoaded().getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
