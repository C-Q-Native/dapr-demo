package org.dapr.demo.service.Dapr;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.Metadata;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonMap;

@Component
public class PubService {
    //Number of messages to be sent.
    private static final int NUM_MESSAGES = 10;

    //Time-to-live for messages published.
    private static final String MESSAGE_TTL_IN_SECONDS = "1000";

    //The title of the topic to be used for publishing
    private static final String TOPIC_NAME = "testingtopic";

    //The name of the pubsub
    private static final String PUBSUB_NAME = "messagebus";

    private DaprClient client;


    public PubService() {
        //this.init();
    }

    public void init(){
        System.out.println("init dapr pubsub client");
        try {
            this.client = new DaprClientBuilder().build();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void publish(String message) throws Exception {
        try (DaprClient daprClient = new DaprClientBuilder().build()) {
            //Publishing messages
            try {
                daprClient.publishEvent(
                        PUBSUB_NAME,
                        TOPIC_NAME,
                        message,
                        singletonMap(Metadata.TTL_IN_SECONDS, MESSAGE_TTL_IN_SECONDS)).block();
                System.out.println("Published message: " + message);
            } catch (Exception e) {
                throw  e;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
