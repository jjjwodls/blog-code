package com.example.notification_polymorphic.notification.controller;

import com.example.notification_polymorphic.notification.dto.NotificationRequest2;
import com.example.notification_polymorphic.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("")
    public String send(@RequestBody @Valid NotificationRequest2 request2){
        return notificationService.send3(request2);
    }
}
