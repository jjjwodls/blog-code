package com.example.spring_event_listener.post.service;


import com.example.spring_event_listener.notification.infrastructure.entity.Notification;
import com.example.spring_event_listener.notification.service.NotificationService;
import com.example.spring_event_listener.post.event.PostCreatedEventFail;
import com.example.spring_event_listener.post.event.PostCreatedEventSuccess;
import com.example.spring_event_listener.post.infrastructure.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostEventHandler {

    private final NotificationService notificationService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    @Async
    public void handlePostCreatedAfterRollback(PostCreatedEventFail event) {
        Post post = event.post();
        log.info("EventListener executed , TransactionalEventListener phase = TransactionPhase.AFTER_ROLLBACK executed");
        notificationService.send(Notification.from(post));
    }
    @EventListener
    @Async
    public void handlePostCreatedAfterRollbackFail(PostCreatedEventFail event){
        Post post = event.post();
        log.info("EventListener executed, Use Default EventListener");
        notificationService.send(Notification.from(post));
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handlePostCreatedAfterCommit(PostCreatedEventSuccess event) throws InterruptedException {
        var post = event.post();
        log.info("TransactionalEventListener phase = TransactionPhase.AFTER_COMMIT executed");
        notificationService.send(Notification.from(post));
    }
}