package com.thoughtworks.springbootemployee.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("not found");
    }
}
