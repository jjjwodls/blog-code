package com.example.spring_event_listener.notification.service;

import com.example.spring_event_listener.notification.infrastructure.SendNotificationInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;


@TestConfiguration
@Slf4j
public class EmailSendTest implements SendNotificationInterface {

    @Override
    public void send() throws InterruptedException {
        log.info("test email send trigger");
        Thread.sleep(1000);
    }
}