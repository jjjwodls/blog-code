package com.example.spring_event_listener.post.controller;

import com.example.spring_event_listener.post.infrastructure.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    private String title;
    private String content;
    private String authorId;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .authorId(authorId)
                .build();
    }
}
