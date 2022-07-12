package org.dapr.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;
import org.dapr.demo.base.response.Result;
import org.dapr.demo.base.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dapr.demo.service.Dapr.PubService;
import org.dapr.demo.base.HttpRequest;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private HttpRequest httpRequest;

    @Autowired
    private PubService pubService;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @RequestMapping(value = "pub")
    public Result<Object> pub () {
        String msg = "this is msg, I say in demo";
        try {
            pubService.publish(msg);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResultResponse.getSuccessResult("Done");
    }

    @Topic(name = "testingtopic", pubsubName = "${myAppProperty:messagebus}")
    @RequestMapping(path = "/sub")
    public Mono<Void> handleMessage(@RequestBody(required = false) CloudEvent<String> cloudEvent) {
        return Mono.fromRunnable(() -> {
            try {
                System.out.println("Subscriber got: " + cloudEvent.getData());
                System.out.println("Subscriber got: " + OBJECT_MAPPER.writeValueAsString(cloudEvent));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @RequestMapping(value = "test")
    public Result<Object> test () {
        return ResultResponse.getSuccessResult("test");
    }


}
