package com.designing.pubsubqueue.service;

public class Publisher extends QueueClient {

    private PublisherWorker publisherWorker;

    public Publisher() {
        super();
    }

    public void setPublisherWorker(PublisherWorker publisherWorker) {
        this.publisherWorker = publisherWorker;
    }

    public void publish() {
        Thread thread = new Thread(publisherWorker);
        thread.start();
    }

}
