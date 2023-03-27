package com.designing.pubsubqueue.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Message {
    private String message;
    private LocalDateTime createdAt;
    public Message(String message) {
        this.message = message;
        createdAt = LocalDateTime.now();
    }
}
