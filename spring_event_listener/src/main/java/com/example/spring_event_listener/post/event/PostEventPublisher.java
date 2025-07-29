package com.example.spring_event_listener.post.event;

import com.example.spring_event_listener.post.infrastructure.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publishCreatedSuccessEvent(Post post) {
        publisher.publishEvent(new PostCreatedEventSuccess(post));
    }

    public void publishCreatedFailEvent(Post post) {
        publisher.publishEvent(new PostCreatedEventFail(post));
    }
}
