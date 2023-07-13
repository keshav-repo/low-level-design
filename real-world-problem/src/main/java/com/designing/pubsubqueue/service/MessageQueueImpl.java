package com.designing.pubsubqueue.service;

import com.designing.pubsubqueue.model.Message;
import com.designing.pubsubqueue.model.Topic;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MessageQueueImpl implements MessageQueue {

    private String messageQueueId;

    private final Map<String, Topic> topicMap;
    private final Map<String, Publisher> publisherMap;
    private final Map<String, Consumer> consumerMap;

    private final Map<String, Map<String, SubscriberWorker>> topicSubscriberWorkerMapping;

    private final ExecutorService executor;

    public MessageQueueImpl() {
        topicMap = new HashMap<>();
        publisherMap = new HashMap<>();
        consumerMap = new HashMap<>();
        topicSubscriberWorkerMapping = new HashMap<>();
        messageQueueId = UUID.randomUUID().toString();
        this.executor = Executors.newFixedThreadPool(6); //.newFixedThreadPool();
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
        executor.submit(()->{
           // publisherWorker
        });
        new Thread(publisherWorker).start();
        // notify the worker that new message is added
        if(topicSubscriberWorkerMapping.containsKey(topicId) && topicSubscriberWorkerMapping.get(topicId).containsKey(publisherId)){
           SubscriberWorker subscriberWorker = topicSubscriberWorkerMapping.get(topicId).get(publisherId);
           subscriberWorker.notify();
        }
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

        if (!topicSubscriberWorkerMapping.containsKey(topicId)) {
            topicSubscriberWorkerMapping.put(topicId, new HashMap<>());
        }
        Map<String, SubscriberWorker> subscriberWorkerMap = topicSubscriberWorkerMapping.get(topicId);
        if (!subscriberWorkerMap.containsKey(subscriberId)) {
            subscriberWorkerMap.put(subscriberId, new SubscriberWorker(topic, consumer));
        }
    }

    @Override
    public void consoleMessage(String subscriberId, String topicId) {
//        if (!topicSubscriberWorkerMapping.containsKey(topicId)) {
//            topicSubscriberWorkerMapping.put(topicId, new HashMap<>());
//        }
//        Map<String, SubscriberWorker> subscriberWorkerMap = topicSubscriberWorkerMapping.get(topicId);
//        if (!subscriberWorkerMap.containsKey(subscriberId)) {
//            Topic topic = topicMap.get(topicId);
//            Consumer consumer = consumerMap.get(subscriberId);
//            subscriberWorkerMap.put(subscriberId, new SubscriberWorker(topic, consumer));
//        }

        Map<String, SubscriberWorker> subscriberWorkerMap = topicSubscriberWorkerMapping.get(topicId);
        SubscriberWorker subscriberWorker = subscriberWorkerMap.get(subscriberId);
        new Thread(subscriberWorker).start();

//        Consumer consumer = consumerMap.get(subscriberId);
//        if (!consumer.getSubscribedTopicIds().contains(topicId)) {
//            throw new RuntimeException("topic not subscribed");
//        }
//        Topic topic = topicMap.get(topicId);
//        topic.getMessageList();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageQueueImpl that = (MessageQueueImpl) o;
        return messageQueueId.equals(that.messageQueueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageQueueId);
    }

    @Override
    public String toString() {
        return "MessageQueueImpl{" +
                "messageQueueId='" + messageQueueId + '\'' +
                '}';
    }
}
