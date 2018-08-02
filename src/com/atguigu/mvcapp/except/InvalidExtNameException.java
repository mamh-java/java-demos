package com.atguigu.mvcapp.except;

public class InvalidExtNameException extends RuntimeException {

    public InvalidExtNameException() {
    }

    public InvalidExtNameException(String message) {
        super(message);
    }
}
