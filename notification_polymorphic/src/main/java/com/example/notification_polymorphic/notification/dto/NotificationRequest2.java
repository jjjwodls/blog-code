package com.example.notification_polymorphic.notification.dto;

import com.example.notification_polymorphic.notification.type.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationRequest2 {

    private final NotificationType type;
    private final String target;
    private final String message;
}
