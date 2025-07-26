package com.example.notification_polymorphic.notification.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationRequest1 {

    private final String type;
    private final String target;
    private final String message;
}
