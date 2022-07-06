package org.dapr.demo.service;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.Metadata;
import static java.util.Collections.singletonMap;

public class DaprService {
    //Number of messages to be sent.
    private static final int NUM_MESSAGES = 10;

    //Time-to-live for messages published.
    private static final String MESSAGE_TTL_IN_SECONDS = "1000";

    //The title of the topic to be used for publishing
    private static final String TOPIC_NAME = "testingtopic";

    //The name of the pubsub
    private static final String PUBSUB_NAME = "messagebus";

    private DaprClient client;

    public void DaprService() {
        this.client = new DaprClientBuilder().build();
    }

    public void publish(String message) throws Exception {
        for (int i = 0; i < NUM_MESSAGES; i++) {
            //Publishing messages
            this.client.publishEvent(
                    PUBSUB_NAME,
                    TOPIC_NAME,
                    message,
                    singletonMap(Metadata.TTL_IN_SECONDS, MESSAGE_TTL_IN_SECONDS)).block();
            System.out.println("Published message: " + message);

            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
