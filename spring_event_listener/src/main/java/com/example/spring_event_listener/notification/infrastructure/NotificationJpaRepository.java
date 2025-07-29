package com.example.spring_event_listener.notification.infrastructure;

import com.example.spring_event_listener.notification.infrastructure.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository  extends JpaRepository<Notification,Long> {
}
