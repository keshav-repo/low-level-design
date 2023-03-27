package com.designing.pubsubqueue.service;

import com.designing.pubsubqueue.model.Message;
import com.designing.pubsubqueue.model.Topic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MessageQueueImpl implements MessageQueue {

    private final Map<String, Topic> topicMap;
    private final Map<String, Publisher> publisherMap;
    private final Map<String, Consumer> consumerMap;

    public MessageQueueImpl() {
        topicMap = new HashMap<>();
        publisherMap = new HashMap<>();
        consumerMap = new HashMap<>();
    }

    @Override
    public Topic addNewTopic(String topicName) {
        Topic topic = new Topic(topicName);
        topicMap.put(topic.getTopicId(), topic);
        return topic;
    }

    @Override
    public Publisher addPublisher() {
        Publisher publisher = new Publisher();
        publisherMap.put(publisher.getid(), publisher);
        return publisher;
    }

    @Override
    public Consumer addConsumer() {
        Consumer consumer = new Consumer();
        consumerMap.put(consumer.getid(), consumer);
        return consumer;
    }

    @Override
    public void publishMessage(String topicId, String publisherId, Message message) {
        Topic topic = topicMap.get(topicId);
        // System.out.println("current Thread: "+Thread.currentThread().getName());
        Publisher publisher = publisherMap.get(publisherId);
        PublisherWorker publisherWorker = new PublisherWorker(topic, message);
        new Thread(publisherWorker).start();
    }

    @Override
    public List<String> getAllMessage(String topicId) {
        Topic topic = topicMap.get(topicId);
        return topic.getMessageList().stream().map(Message::getMessage).collect(Collectors.toList());
    }

    @Override
    public void subscribeTopic(String topicId, String subscriberId) {
        Topic topic = topicMap.get(topicId);
        topic.addSubscriberList(consumerMap.get(subscriberId));
        Consumer consumer = consumerMap.get(subscriberId);
        consumer.addTopicId(topicId);
    }

    @Override
    public void consoleMessage(String subscriberId, String topicId) {
        Consumer consumer = consumerMap.get(subscriberId);
        if (!consumer.getSubscribedTopicIds().contains(topicId)) {
            throw new RuntimeException("topic not subscribed");
        }
        Topic topic = topicMap.get(topicId);
    }

}
