package com.example.notification_polymorphic.notification.service;

import com.example.notification_polymorphic.notification.dto.NotificationRequest1;
import com.example.notification_polymorphic.notification.dto.NotificationRequest2;
import com.example.notification_polymorphic.notification.type.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    /**
     * if 문을 통한 분기
     */
    public String send1(NotificationRequest1 notificationRequest1) throws BadRequestException {
        String type = notificationRequest1.getType();
        String message = notificationRequest1.getMessage();
        String target = notificationRequest1.getTarget();
        if (type.equals("SMS")) {
            isPhoneNumber(target);
            log.info("SMS 전송을 하였습니다. 대상자 : {} , message : {}", target, message);
            return "SMS";
        } else if (type.equals("EMAIL")) {
            isEmailFormat(target);
            log.info("EMAIL 전송을 하였습니다. 대상자 : {} , message : {}", target, message);
            return "EMAIL";
        } else if (type.equals("PUSH")) {
            isPushId(target);
            log.info("EMAIL 전송을 하였습니다. 대상자 : {} , message : {}", target, message);
            return "PUSH";
        } else{
            throw new BadRequestException("잘못된 타입의 알림 요청하였습니다. 요청 타입 = " + type);
        }
    }

    public void isPhoneNumber(String target){
        log.info("phone number target check");
    }

    public void isEmailFormat(String target){
        log.info("email target check");
    }

    public void isPushId(String target){
        log.info("push id target check");
    }

    public String send2(NotificationRequest1 request) throws BadRequestException {
        NotificationType notificationType;
        try {
            notificationType = NotificationType.valueOf(request.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("지원하지 않는 알림 타입입니다: " + request.getType());
        }
        String target = request.getTarget();
        String message = request.getMessage();
        notificationType.validateTarget(target);
        return notificationType.send(target, message);
    }

    public String send3(NotificationRequest2 request) {
        NotificationType notificationType = request.getType();
        String target = request.getTarget();
        String message = request.getMessage();
        notificationType.validateTarget(target);
        return notificationType.send(target, message);
    }
}
