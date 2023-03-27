package com.designing.pubsubqueue.service;

import com.designing.pubsubqueue.model.Message;
import com.designing.pubsubqueue.model.Topic;

import java.util.List;
import java.util.Scanner;

public class MessageQueueDriver {
    public static void main(String[] args) throws InterruptedException{
        MessageQueue messageQueue = new MessageQueueImpl();


        Topic topic = messageQueue.addNewTopic("location");

        Publisher publisher = messageQueue.addPublisher();

        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("10.093587,77.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("13.093587,77.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("14.093587,78.570075"));

        Consumer consumer1 = messageQueue.addConsumer();
        Consumer consumer2 = messageQueue.addConsumer();
        Consumer consumer3 = messageQueue.addConsumer();
        messageQueue.subscribeTopic(topic.getTopicId(), consumer1.getid());
        messageQueue.subscribeTopic(topic.getTopicId(), consumer2.getid());
        messageQueue.subscribeTopic(topic.getTopicId(), consumer3.getid());


        System.out.println("get all message in test consumer");
        messageQueue.consoleMessage(consumer1.getid(), topic.getTopicId());
        messageQueue.consoleMessage(consumer2.getid(), topic.getTopicId());
        messageQueue.consoleMessage(consumer3.getid(), topic.getTopicId());

        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("15.093587,79.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("16.093587,78.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("17.093587,79.570075"));

        // test case II
//        Topic topic2 = messageQueue.addNewTopic("message");
//
//        Publisher publisher2 = messageQueue.addPublisher();
//        messageQueue.publishMessage(topic2.getTopicId(), publisher2.getid(), new Message("Hi Hello"));
//
//        Consumer consumer = messageQueue.addConsumer();
//        messageQueue.subscribeTopic(topic2.getTopicId(), consumer.getid());
//        messageQueue.consoleMessage(consumer.getid(), topic2.getTopicId());
//
//        messageQueue.publishMessage(topic2.getTopicId(), publisher2.getid(), new Message("How are you"));
//        messageQueue.publishMessage(topic2.getTopicId(), publisher2.getid(), new Message("I am fine"));

    }
}
