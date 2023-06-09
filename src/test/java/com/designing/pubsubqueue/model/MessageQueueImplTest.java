package com.designing.pubsubqueue.model;

import com.designing.pubsubqueue.service.Consumer;
import com.designing.pubsubqueue.service.MessageQueue;
import com.designing.pubsubqueue.service.MessageQueueImpl;
import com.designing.pubsubqueue.service.Publisher;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MessageQueueImplTest {

    private final MessageQueue messageQueue;
    private Topic topic;
    public MessageQueueImplTest() {
        messageQueue = new MessageQueueImpl();
        topic = messageQueue.addNewTopic("location");
    }



    @Test
    public void test() throws InterruptedException {
        Publisher publisher = messageQueue.addPublisher();

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
       // System.out.println(messageQueue.getAllMessage(topic.getTopicId()));

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

        Thread.sleep(10000);
        System.out.println("after consuming message from all subscriber");
        messageQueue.consoleMessage(consumer1.getid(), topic.getTopicId());
    }

}
