package com.example.spring_event_listener;

import com.example.spring_event_listener.notification.infrastructure.NotificationJpaRepository;
import com.example.spring_event_listener.notification.service.EmailSendTest;
import com.example.spring_event_listener.notification.service.NotificationService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public NotificationService notificationService(NotificationJpaRepository notificationJpaRepository){
        return NotificationService.builder()
                .notificationJpaRepository(notificationJpaRepository)
                .emailSend(new EmailSendTest())
                .build();
    }
}
