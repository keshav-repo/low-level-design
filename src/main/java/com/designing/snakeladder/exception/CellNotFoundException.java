package com.designing.snakeladder.exception;

public class CellNotFoundException extends RuntimeException{
    public CellNotFoundException(String message) {
        super(message);
    }
}
