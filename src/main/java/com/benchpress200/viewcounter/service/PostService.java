package com.benchpress200.viewcounter.service;

import com.benchpress200.viewcounter.controller.request.PostCreateRequest;
import com.benchpress200.viewcounter.domain.Post;
import com.benchpress200.viewcounter.repository.PostRepository;
import com.benchpress200.viewcounter.service.result.PostResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long createPost(PostCreateRequest request) {
        Post post = request.toEntity();
        post = postRepository.save(post);

        return post.getId();
    }


    @Transactional
    public PostResult getPostBasic(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow();

        post.incrementViewCount();

        return PostResult.from(post);
    }

    @Transactional
    public PostResult getPostWithPessimisticLock(Long id) {
        Post post = postRepository.findByIdWithPessimisticLock(id)
                .orElseThrow();

        post.incrementViewCount();

        return PostResult.from(post);
    }

}
