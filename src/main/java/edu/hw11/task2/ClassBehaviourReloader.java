package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public final class ClassBehaviourReloader {

    private ClassBehaviourReloader() {
    }

    /**
     * Should be called after installing the agent {@link net.bytebuddy.agent.ByteBuddyAgent ByteBuddyAgent}
     */
    public static void reload() {
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(Delegate.class))
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }

    private final static class Delegate {
        public static int sum(int a, int b) {
            return a * b;
        }
    }

}
