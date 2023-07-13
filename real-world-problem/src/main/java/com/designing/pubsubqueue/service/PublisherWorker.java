package com.designing.pubsubqueue.service;

import com.designing.pubsubqueue.model.Message;
import com.designing.pubsubqueue.model.Topic;

public class PublisherWorker implements Runnable{

    private final Topic topic;
    private final Message message;

    public PublisherWorker(final Topic topic,final Message message) {
        this.topic = topic;
        this.message = message;
    }

    @Override
    public void run() {
        topic.addMessage(message);
    }
}
