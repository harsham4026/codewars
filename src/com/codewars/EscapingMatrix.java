package com.codewars;

/**
 * https://www.codewars.com/kata/escaping-the-matrix/
 */
public class EscapingMatrix {

    public static void enter() {
        throwException(new Neo());
    }

    //ref - https://stackoverflow.com/questions/4519557/is-there-a-way-to-throw-an-exception-without-adding-the-throws-declaration
    private static <T extends Throwable> void throwException(Throwable throwable) throws T {
        throw (T) throwable;
    }
    public static final class Neo extends Exception {}
}
