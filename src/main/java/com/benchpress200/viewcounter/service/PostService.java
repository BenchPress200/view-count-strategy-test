package com.benchpress200.viewcounter.service;

import com.benchpress200.viewcounter.domain.Post;
import com.benchpress200.viewcounter.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostResult getPostBasic(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow();

        post.incrementViewCount();

        return PostResult.from(post);
    }

}
