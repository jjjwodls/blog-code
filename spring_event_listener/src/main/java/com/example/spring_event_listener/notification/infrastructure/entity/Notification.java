package com.example.spring_event_listener.notification.infrastructure.entity;

import com.example.spring_event_listener.post.infrastructure.Post;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String target; // 수신자 정보

    private String title;

    @Lob
    private String message;

    @CreatedDate
    private LocalDateTime createdAt;

    public static Notification from(Post post){
        return Notification.builder()
                .target("target")
                .title(post.getTitle())
                .message(post.getContent())
                .build();
    }
}
