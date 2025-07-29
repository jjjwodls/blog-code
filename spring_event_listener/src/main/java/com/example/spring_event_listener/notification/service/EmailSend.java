package com.example.spring_event_listener.notification.service;

import com.example.spring_event_listener.notification.infrastructure.SendNotificationInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSend implements SendNotificationInterface {
    @Override
    public void send() {
      log.info("real send email");
    }
}
