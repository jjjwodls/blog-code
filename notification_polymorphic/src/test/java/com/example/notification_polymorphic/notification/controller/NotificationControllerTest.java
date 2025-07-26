package com.example.notification_polymorphic.notification.controller;

import com.example.notification_polymorphic.notification.dto.NotificationRequest2;
import com.example.notification_polymorphic.notification.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NotificationService notificationService;

    @Test
    @DisplayName("enum 타입이 잘못되어 요청에 실패한다.")
    void create() throws Exception {
        String invalidJson = """
            {
                "type": "KAKAOTALK",
                "target": "01012345678",
                "message": "테스트 메시지"
            }
            """;
        mockMvc.perform(
                post("/notification")
                        .content(invalidJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}