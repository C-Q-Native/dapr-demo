package org.dapr.demo.controller;
import io.dapr.actors.ActorId;
import io.dapr.actors.client.ActorClient;
import io.dapr.actors.client.ActorProxyBuilder;

import java.util.ArrayList;
import java.util.List;

import org.dapr.demo.base.response.Result;
import org.dapr.demo.base.response.ResultResponse;
import org.dapr.demo.service.Dapr.Actors.Actor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {
    private static final int NUM_ACTORS = 3;
    @RequestMapping(value = "actorTest")
    public Result<Object> actorTest () {
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<Actor> builder = new ActorProxyBuilder(Actor.class, client);
            ActorId actorId = ActorId.createRandom();
            Actor actor = builder.build(actorId);

            // Invoke the 'say' method in actor.
            String message = String.format("Actor %s said message #%d", actorId, "hello");
            String result = actor.say(message);
            System.out.println(String.format("Actor %s got a reply: %s", actorId, result));
        }

        return ResultResponse.getSuccessResult("hello , actor Done");
    }

}