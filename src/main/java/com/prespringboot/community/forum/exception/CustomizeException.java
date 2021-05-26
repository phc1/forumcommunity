package com.prespringboot.community.forum.exception;

public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public String getMessage() {
        return message;
    }
}
