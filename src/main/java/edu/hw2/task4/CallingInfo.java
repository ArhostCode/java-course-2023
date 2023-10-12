package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {

    public static CallingInfo callingInfo() {
        StackTraceElement element = new Throwable().getStackTrace()[1];
        return new CallingInfo(element.getClassName(), element.getMethodName());
    }
}
