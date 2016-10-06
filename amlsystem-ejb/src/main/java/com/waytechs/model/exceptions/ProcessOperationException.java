package com.waytechs.model.exceptions;

public class ProcessOperationException extends Exception {

    private static final long serialVersionUID = 1L;

    public ProcessOperationException(String message) {
        super(message);
    }

    public ProcessOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}
