package com.designing.bookmyshow.exception;

public class SeatNotFound extends RuntimeException{
    public SeatNotFound(String message) {
        super(message);
    }
}
