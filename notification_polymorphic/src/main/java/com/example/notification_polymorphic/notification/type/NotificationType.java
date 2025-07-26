package com.example.notification_polymorphic.notification.type;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public enum NotificationType {
    SMS {
        @Override
        public String send(String target, String message) {
            log.info("SMS 전송을 하였습니다. 대상자 : {} , message : {}", target, message);
            return "SMS";
        }
        @Override
        public void validateTarget(String target) {
            log.info("phone number target check");
        }
    },

    EMAIL {
        @Override
        public String send(String target, String message) {
            log.info("EMAIL 전송을 하였습니다. 대상자 : {} , message : {}", target, message);
            return "EMAIL";
        }
        @Override
        public void validateTarget(String target) {
            log.info("EMAIL number target check");
        }
    },

    PUSH {
        @Override
        public String send(String target, String message) {
            log.info("EMAIL 전송을 하였습니다. 대상자 : {} , message : {}", target, message);
            return "PUSH";
        }
        @Override
        public void validateTarget(String target) {
            log.info("push id target check");
        }
    };

    public abstract String send(String target, String message);
    public abstract void validateTarget(String target);
}
