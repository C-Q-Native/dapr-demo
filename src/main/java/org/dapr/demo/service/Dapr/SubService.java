package org.dapr.demo.service.Dapr;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dapr.Rule;
import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Component
public class SubService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Topic(name = "testingtopic", pubsubName = "${myAppProperty:messagebus}")
    @GetMapping(path = "/testingtopic")
    public Mono<Void> handleMessage(@RequestBody(required = false) CloudEvent<String> cloudEvent) {
        return Mono.fromRunnable(() -> {
            try {
                System.out.println("Subscriber got: " + cloudEvent.getData());
                System.out.println("Subscriber got: " + OBJECT_MAPPER.writeValueAsString(cloudEvent));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /*
    @Topic(name = "testingtopic", pubsubName = "${myAppProperty:messagebus}",
            rule = @Rule(match = "event.type == \"v2\"", priority = 1))
    @PostMapping(path = "/testingtopicV2")
    public Mono<Void> handleMessageV2(@RequestBody(required = false) CloudEvent cloudEvent) {
        return Mono.fromRunnable(() -> {
            try {
                System.out.println("Subscriber got: " + cloudEvent.getData());
                System.out.println("Subscriber got: " + OBJECT_MAPPER.writeValueAsString(cloudEvent));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }*/
}
