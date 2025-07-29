package com.example.spring_event_listener.post.service;

import com.example.spring_event_listener.post.controller.PostCreateRequest;
import com.example.spring_event_listener.post.event.PostEventPublisher;
import com.example.spring_event_listener.post.infrastructure.Post;
import com.example.spring_event_listener.post.infrastructure.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final PostEventPublisher eventPublisher;

    @Transactional
    public Post createPostSuccess(PostCreateRequest postCreateRequest) {
        Post post = postCreateRequest.toEntity();
        Post saved = postJpaRepository.save(post);
        eventPublisher.publishCreatedSuccessEvent(saved);
        return saved;
    }

    @Transactional
    public Post createPostFail(PostCreateRequest postCreateRequest) {
        Post post = postCreateRequest.toEntity();
        Post saved = postJpaRepository.save(post);
        eventPublisher.publishCreatedFailEvent(saved);
        throw new RuntimeException();
    }


}
