package com.benchpress200.viewcounter.service.result;

import com.benchpress200.viewcounter.domain.Post;
import com.benchpress200.viewcounter.domain.PostWithVersion;
import lombok.Getter;

@Getter
public class PostResult {
    private final Long id;
    private final String title;
    private final Long viewCount;

    private PostResult(
            Long id,
            String title,
            Long viewCount
    ) {
        this.id = id;
        this.title = title;
        this.viewCount = viewCount;
    }

    public static PostResult from(Post post) {
        return new PostResult(post.getId(), post.getTitle(), post.getViewCount());
    }

    public static PostResult from(PostWithVersion post) {
        return new PostResult(post.getId(), post.getTitle(), post.getViewCount());
    }
}
