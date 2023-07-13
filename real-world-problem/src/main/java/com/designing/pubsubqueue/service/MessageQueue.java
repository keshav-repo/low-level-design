package com.designing.pubsubqueue.service;

import com.designing.pubsubqueue.model.Message;
import com.designing.pubsubqueue.model.Topic;

import java.util.List;

public interface MessageQueue {

    //Return topic new topic created
    public Topic addNewTopic(String topicName);

    // Get publishedId when adding a publisher
    public Publisher addPublisher();

    public Consumer addConsumer();

    public void publishMessage(String topicId, String publisherId, Message message);

    public List<String> getAllMessage(String topicId);

    public void subscribeTopic(String topicId, String subscriberId);

    public void consoleMessage(String subscriberId, String topicId);

}
