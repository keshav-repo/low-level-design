package com.designing.pubsubqueue.service;

import com.designing.pubsubqueue.model.Message;

import java.util.UUID;

public class QueueClient {
    private final String id;
    public QueueClient() {
        this.id = UUID.randomUUID().toString();
    }
    public String getid() {
        return id;
    }
}
