package com.designing.pubsubqueue.service;

import com.designing.pubsubqueue.model.Message;
import com.designing.pubsubqueue.model.Topic;

import java.util.concurrent.atomic.AtomicInteger;

public class SubscriberWorker implements Runnable {

    private final AtomicInteger offset;
    private final Topic topic;
    private final Consumer consumer;

    public SubscriberWorker(Topic topic, Consumer consumer) {
        this.offset = new AtomicInteger(0);
        this.topic = topic;
        this.consumer = consumer;
    }

    /*
     synchronized (topicSubscriber) {
            do {
                int curOffset = topicSubscriber.getOffset().get();
                while (curOffset >= topic.getMessages().size()) {
                    topicSubscriber.wait();
                }
                Message message = topic.getMessages().get(curOffset);
                topicSubscriber.getSubscriber().consume(message);

                // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                // consuming we need to increase only if it was previous one.
                topicSubscriber.getOffset().compareAndSet(curOffset, curOffset + 1);

            } while (true);
        }
     */
    @Override
    public void run() {
        while (offset.get() < topic.getMessageList().size()) {
            int currOffset = offset.get();
//            while(currOffset>=topic.getMessageList().size()){
//                try{
//                    this.wait();
//                }catch (InterruptedException interruptedException) {
//                }
//            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
            }

            Message message = topic.getMessageList().get(currOffset);
            consumer.consumeMessage(message);
            offset.compareAndSet(currOffset, currOffset + 1);
        }
    }

}
