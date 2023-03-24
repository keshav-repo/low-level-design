package com.designing.snakeladder.exception;

public class GameNotStarted extends RuntimeException{
    public GameNotStarted(String message) {
        super(message);
    }
}
