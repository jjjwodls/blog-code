package com.example.notification_polymorphic.notification.service;

import com.example.notification_polymorphic.notification.dto.NotificationRequest1;
import com.example.notification_polymorphic.notification.dto.NotificationRequest2;
import com.example.notification_polymorphic.notification.type.NotificationType;
import org.apache.coyote.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;


    @Test
    @DisplayName("이메일 전송을 보낼 수 있다. (if-else)")
    void sendEmail1() throws BadRequestException {
        NotificationRequest1 notificationRequest1 =
                NotificationRequest1.builder().type("EMAIL").message("이메일 보냅니다")
                        .target("teest@gmail.com").build();
        String sendType = notificationService.send1(notificationRequest1);
        assertThat(sendType).isEqualTo("EMAIL");
    }

    @Test
    @DisplayName("이메일 전송을 보낼 수 있다.(enum)")
    void sendEmail2() throws BadRequestException {
        NotificationRequest1 notificationRequest1 =
                NotificationRequest1.builder().type("EMAIL").message("이메일 보냅니다")
                        .target("teest@gmail.com").build();
        String sendType = notificationService.send2(notificationRequest1);
        assertThat(sendType).isEqualTo("EMAIL");
    }

    @Test
    @DisplayName("이메일 전송을 보낼 수 있다.(enum 객체를 통한 전송)")
    void sendEmail3() {
        NotificationRequest2 notificationRequest2 =
                NotificationRequest2.builder().type(NotificationType.EMAIL).message("이메일 보냅니다")
                        .target("teest@gmail.com").build();
        String sendType = notificationService.send3(notificationRequest2);
        assertThat(sendType).isEqualTo("EMAIL");
    }
}