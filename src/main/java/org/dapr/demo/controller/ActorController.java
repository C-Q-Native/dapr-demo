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
            List<Thread> threads = new ArrayList<>(NUM_ACTORS);

            // Creates multiple actors.
            for (int i = 0; i < NUM_ACTORS; i++) {
                ActorId actorId = ActorId.createRandom();
                Actor actor = builder.build(actorId);

                // Start a thread per actor.
                Thread thread = new Thread(() -> callActorForever(actorId.toString(), actor));
                thread.start();
                threads.add(thread);
            }

            // Waits for threads to finish.
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        }

        return ResultResponse.getSuccessResult("hello , actor Done");
    }

    private static final void callActorForever(String actorId, Actor actor) {
        // First, register reminder.
        actor.registerReminder();

        // Now, we run until thread is interrupted.
        while (!Thread.currentThread().isInterrupted()) {
            // Invoke actor method to increment counter by 1, then build message.
            int messageNumber = actor.incrementAndGet(1).block();
            String message = String.format("Actor %s said message #%d", actorId, messageNumber);

            // Invoke the 'say' method in actor.
            String result = actor.say(message);
            System.out.println(String.format("Actor %s got a reply: %s", actorId, result));

            try {
                // Waits for up to 1 second.
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                // We have been interrupted, so we set the interrupted flag to exit gracefully.
                Thread.currentThread().interrupt();
            }
        }
    }

}