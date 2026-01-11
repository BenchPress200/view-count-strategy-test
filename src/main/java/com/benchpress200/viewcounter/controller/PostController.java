package com.benchpress200.viewcounter.controller;

import com.benchpress200.viewcounter.controller.request.PostCreateRequest;
import com.benchpress200.viewcounter.service.result.PostResult;
import com.benchpress200.viewcounter.service.PostService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(
            @RequestBody PostCreateRequest request
    ) {
        Long id = postService.createPost(request);
        String locationStr = String.format("/posts/%s", id);
        URI location = URI.create(locationStr);

        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping("/posts/{postId}/basic")
    public ResponseEntity<?> getPostBasic(
            @PathVariable("postId") Long postId
    ) {
        PostResult result = postService.getPostBasic(postId);

        return ResponseEntity.ok()
                .body(result);
    }
}
