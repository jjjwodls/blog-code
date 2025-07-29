package com.example.spring_event_listener.notification.service;

import com.example.spring_event_listener.notification.infrastructure.NotificationJpaRepository;
import com.example.spring_event_listener.notification.infrastructure.SendNotificationInterface;
import com.example.spring_event_listener.notification.infrastructure.entity.Notification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationJpaRepository notificationJpaRepository;

    private final SendNotificationInterface emailSend;

    @Transactional
    public Notification send(Notification notification){
        try {
            emailSend.send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return notificationJpaRepository.save(notification);
    }

    public Notification getById(Long id){
        return notificationJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id is not found id" + id));
    }

    public List<Notification> findAll(){
        return notificationJpaRepository.findAll();
    }

    public void deleteAll() {
        notificationJpaRepository.deleteAll();
    }
}
