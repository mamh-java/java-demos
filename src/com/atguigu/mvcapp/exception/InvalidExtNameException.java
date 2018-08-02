package com.atguigu.mvcapp.exception;

public class InvalidExtNameException extends RuntimeException {

    public InvalidExtNameException() {
    }

    public InvalidExtNameException(String message) {
        super(message);
    }
}
