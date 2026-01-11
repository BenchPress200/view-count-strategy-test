package com.benchpress200.viewcounter.service;

import com.benchpress200.viewcounter.controller.request.PostCreateRequest;
import com.benchpress200.viewcounter.domain.Post;
import com.benchpress200.viewcounter.domain.PostWithVersion;
import com.benchpress200.viewcounter.repository.PostRepository;
import com.benchpress200.viewcounter.repository.PostWithVersionRepository;
import com.benchpress200.viewcounter.service.result.PostResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostWithVersionRepository postWithVersionRepository;
    private final RedisTemplate<String, Long> redisTemplate;

    @Transactional
    public Long createPost(PostCreateRequest request) {
        Post post = request.toEntity();
        post = postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public Long createPostWithVersion(PostCreateRequest request) {
        PostWithVersion post = request.toEntityWithVersion();
        post = postWithVersionRepository.save(post);

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

    @Transactional
    public PostResult getPostWithOptimisticLock(Long id) {
        return getPostWithVersion(id);
    }

    @Transactional
    public PostResult getPostWithAtomic(Long id) {
        postRepository.incrementViewCount(id);

        Post post = postRepository.findById(id)
                .orElseThrow();

        return PostResult.from(post);
    }

    public PostResult getPostWithRedis(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow();

        Long extraViewCount = redisTemplate.opsForValue().increment("post:view:" + id);
        post.addViewCount(extraViewCount);

        return PostResult.from(post);
    }

    private PostResult getPostWithVersion(Long id) {
        try {
            PostWithVersion post = postWithVersionRepository.findById(id)
                    .orElseThrow();

            post.incrementViewCount();

            return PostResult.from(post);
        } catch (ObjectOptimisticLockingFailureException e) {
            return getPostWithVersion(id);
        }
    }
}
