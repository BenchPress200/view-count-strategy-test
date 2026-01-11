package com.benchpress200.viewcounter.controller.request;

import com.benchpress200.viewcounter.domain.Post;
import lombok.Getter;

@Getter
public class PostCreateRequest {
    private String title;

    public Post toEntity() {
        return Post.of(title);
    }
}
