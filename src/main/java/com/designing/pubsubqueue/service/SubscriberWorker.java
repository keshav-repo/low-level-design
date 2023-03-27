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

    @Override
    public void run() {
        //while (offset.get() < topic.getMessageList().size())
       synchronized (this){
           while (true) {
               int currOffset = offset.get();

               while (currOffset >= topic.getMessageList().size()) {
                   try {
                       wait();
                   } catch (Exception interruptedException) {
                       System.out.println(interruptedException.getLocalizedMessage());
                       interruptedException.printStackTrace();
                   }
               }
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

}
