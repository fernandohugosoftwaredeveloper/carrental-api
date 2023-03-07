package com.bee.carrental.api.exception;

import org.springframework.dao.DataAccessException;

public class ModelAccessException extends RuntimeException {
    public ModelAccessException(String message, DataAccessException ex) {
        super(message);
    }
}