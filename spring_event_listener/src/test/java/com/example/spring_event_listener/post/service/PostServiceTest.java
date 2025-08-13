package com.example.spring_event_listener.post.service;

import com.example.spring_event_listener.TestConfig;
import com.example.spring_event_listener.notification.service.NotificationService;
import com.example.spring_event_listener.post.controller.PostCreateRequest;
import com.example.spring_event_listener.post.infrastructure.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.awaitility.Awaitility.await;


@SpringBootTest
@Import(TestConfig.class)
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService.deleteAll(); // 알림 이력 초기화
    }

    @Test
    @DisplayName("게시글 생성은 실패하고 실패에 대한 notification 이 해당하는 이벤트 리스너가 수신하여 2개 생성된다.")
    void createPostFailAndSendNotification() {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                .title("title")
                .content("content")
                .authorId("1L").build();
        assertThatThrownBy(() -> postService.createPostFail(postCreateRequest)).isInstanceOf(RuntimeException.class);
        await().atMost(Duration.ofSeconds(2))
                .untilAsserted(() -> assertThat(notificationService.findAll()).hasSize(2));
    }

    @Test
    @DisplayName("게시글 생성이 정상적으로 된 후 notification 은 정상적으로 1번만 발송된다.")
    void createSuccessAndSendNotification() {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                .title("title")
                .content("content")
                .authorId("1L").build();
        postService.createPostSuccess(postCreateRequest);
        await().atMost(Duration.ofSeconds(2))
                .pollInterval(Duration.ofMillis(200)) // 200ms마다 확인
                .untilAsserted(() -> assertThat(notificationService.findAll()).hasSize(1));
    }

    @Test
    void objectMapperTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                .title("title")
                .content("content")
                .authorId("1L").build();
        String s = objectMapper.writeValueAsString(postCreateRequest);
        PostCreateRequest convertPostRequest = objectMapper.readValue(s, PostCreateRequest.class);
        s += "1";
        PostCreateRequest convertPostRequest2 = objectMapper.readValue(s, PostCreateRequest.class);
        System.out.println("convertPostRequest2 = " + convertPostRequest2);
    }
}