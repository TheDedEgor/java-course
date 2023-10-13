package edu.hw2.task4;

public final class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo(Throwable ex) {
        var exceptionSource = ex.getStackTrace()[0];
        return new CallingInfo(exceptionSource.getClassName(), exceptionSource.getMethodName());
    }
}
