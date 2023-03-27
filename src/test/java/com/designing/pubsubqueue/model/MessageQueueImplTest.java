package com.designing.pubsubqueue.model;

import com.designing.pubsubqueue.service.Consumer;
import com.designing.pubsubqueue.service.MessageQueue;
import com.designing.pubsubqueue.service.MessageQueueImpl;
import com.designing.pubsubqueue.service.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

public class MessageQueueImplTest {

    private final MessageQueue messageQueue;

    public MessageQueueImplTest() {
        messageQueue = new MessageQueueImpl();
    }

    @BeforeEach
    public void init() {

    }

    @Test
    @Timeout(2)
    public void test() throws InterruptedException {
        Publisher publisher = messageQueue.addPublisher();
        Topic topic = messageQueue.addNewTopic("location");
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("10.093587,77.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("11.093587,77.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("12.093587,77.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("13.093587,77.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("14.093587,78.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("15.093587,79.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("16.093587,78.570075"));
        messageQueue.publishMessage(topic.getTopicId(), publisher.getid(), new Message("17.093587,79.570075"));

        List<String> meStringList = messageQueue.getAllMessage(topic.getTopicId());

        // since all the message are coming in different thread, time taken by each of one is different
        System.out.println(messageQueue.getAllMessage(topic.getTopicId()));

        Thread.sleep(1100);
        // after one second, all the message is populated
        System.out.println(messageQueue.getAllMessage(topic.getTopicId()));

        Consumer consumer = messageQueue.addConsumer();
        messageQueue.subscribeTopic(topic.getTopicId(), consumer.getid());
    }

}
