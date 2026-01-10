package com.benchpress200.viewcounter.controller;

import com.benchpress200.viewcounter.service.PostResult;
import com.benchpress200.viewcounter.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts/{postId}/basic")
    public ResponseEntity<?> getPostBasic(
            @PathVariable("postId") Long postId
    ) {
        PostResult result = postService.getPostBasic(postId);

        return ResponseEntity.ok()
                .body(result);
    }
}
