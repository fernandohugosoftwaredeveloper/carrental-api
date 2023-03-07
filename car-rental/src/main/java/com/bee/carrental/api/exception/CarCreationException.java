package com.bee.carrental.api.exception;


public class CarCreationException extends RuntimeException {
    public CarCreationException(String message, Exception ex) {
        super(message);
    }
}