package com.example.spring_event_listener.post.controller;

import com.example.spring_event_listener.post.infrastructure.Post;
import com.example.spring_event_listener.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @PostMapping("/fail")
    public ResponseEntity<Void> createFail(@RequestBody PostCreateRequest request) {
        postService.createPostFail(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/success")
    public ResponseEntity<Long> createSuccess(@RequestBody PostCreateRequest request) {
        Post postSuccess = postService.createPostSuccess(request);
        return ResponseEntity.ok(postSuccess.getId());
    }
}
