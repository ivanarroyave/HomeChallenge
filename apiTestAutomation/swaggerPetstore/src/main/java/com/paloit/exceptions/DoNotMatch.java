package com.paloit.exceptions;

public class DoNotMatch extends AssertionError {
    public static final String VALIDATION_DO_NOT_MATCH = "The validation don't match. %s";

    public DoNotMatch(String message, Throwable cause){
        super(message, cause);
    }

    public DoNotMatch(String message){
        super(message, null);
    }
}
