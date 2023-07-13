package com.designing.pubsubqueue.model;

import com.designing.pubsubqueue.service.Consumer;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Topic {
    private String topicId;
    private String topicName;
    private List<Message> messageList;
    private List<Consumer> subscriberList;
    public Topic(String topicName) {
        this.topicId = UUID.randomUUID().toString();
        this.topicName = topicName;
        this.messageList = new CopyOnWriteArrayList<>();
        this.subscriberList = new CopyOnWriteArrayList<>();
    }
    public void addMessage(Message message){
        this.messageList.add(message);
    }
    public void addSubscriberList(Consumer consumer) {
        this.subscriberList.add(consumer);
    }
    public String getTopicId() {
        return topicId;
    }
    public String getTopicName() {
        return topicName;
    }
    public List<Message> getMessageList() {
        return messageList;
    }
}
