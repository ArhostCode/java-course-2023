package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {

    public static CallingInfo callingInfo() {
        try {
            throw new IllegalStateException();
        } catch (Exception exception) {
            StackTraceElement element = exception.getStackTrace()[1];
            return new CallingInfo(element.getClassName(), element.getMethodName());
        }
    }
}
