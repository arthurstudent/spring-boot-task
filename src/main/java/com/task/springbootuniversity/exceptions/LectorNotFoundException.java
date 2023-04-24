package com.task.springbootuniversity.exceptions;

public class LectorNotFoundException extends RuntimeException {
    public LectorNotFoundException(String message) {
        super(message);
    }
}
