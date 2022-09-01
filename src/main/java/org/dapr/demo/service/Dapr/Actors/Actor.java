package org.dapr.demo.service.Dapr.Actors;
import io.dapr.actors.ActorMethod;
import io.dapr.actors.ActorType;
import reactor.core.publisher.Mono;

@ActorType(name = "Actor")
public interface Actor {

    void registerReminder();

    @ActorMethod(name = "say")
    String say(String something);

    void clock(String message);
}
