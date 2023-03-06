package com.bee.carrental.api.exception;

public class InvalidCarDataException extends RuntimeException {
    public InvalidCarDataException(String message) {
        super(message);
    }
}