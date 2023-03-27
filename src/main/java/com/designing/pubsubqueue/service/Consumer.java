package com.designing.pubsubqueue.service;

import java.util.ArrayList;
import java.util.List;

public  class Consumer extends QueueClient{

    final List<String> subscribedTopicIds;
    public Consumer() {
        super();
        subscribedTopicIds = new ArrayList<>();
    }

    public void addTopicId(String subscribedTopicId){
        this.subscribedTopicIds.add(subscribedTopicId);
    }

    public List<String> getSubscribedTopicIds() {
        return subscribedTopicIds;
    }

    public  List<String> consume(String topicId){
        return null;
    }
    public  void subscribeTopic(String topicId){
        return;
    }
}
