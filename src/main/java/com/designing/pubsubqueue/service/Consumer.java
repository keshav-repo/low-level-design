package com.designing.pubsubqueue.service;

import com.designing.pubsubqueue.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends QueueClient {
    final List<String> subscribedTopicIds;
    final Map<String, AtomicInteger> topicOffsetMapping;

    public Consumer() {
        super();
        this.subscribedTopicIds = new ArrayList<>();
        this.topicOffsetMapping = new ConcurrentHashMap<>();
    }

    public void addTopicId(String subscribedTopicId) {
        this.subscribedTopicIds.add(subscribedTopicId);
    }

    public List<String> getSubscribedTopicIds() {
        return subscribedTopicIds;
    }

    public List<String> consume(String topicId) {
        return null;
    }

    public void subscribeTopic(String topicId) {
        return;
    }

    public void setOffsetForTopic(String topicId, int offset) {
        this.topicOffsetMapping.put(topicId, new AtomicInteger(offset));
    }

    public void consumeMessage(Message message) {
        System.out.println( message.getMessage());
    }

}
