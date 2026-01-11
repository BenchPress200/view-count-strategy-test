package com.benchpress200.viewcounter.controller;

import com.benchpress200.viewcounter.controller.request.PostCreateRequest;
import com.benchpress200.viewcounter.service.result.PostResult;
import com.benchpress200.viewcounter.service.PostService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /*
     * 게시글 생성
     */
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

    /*
     * 게시글 생성 - 버전
     */
    @PostMapping("/posts-version")
    public ResponseEntity<?> createPostWithVersion(
            @RequestBody PostCreateRequest request
    ) {
        Long id = postService.createPostWithVersion(request);
        String locationStr = String.format("/posts/%s", id);
        URI location = URI.create(locationStr);

        return ResponseEntity
                .created(location)
                .build();
    }

    /*
     * 게시글 조회 - JPA 변경 감지 조회수 업데이트
     */
    @GetMapping("/posts/{postId}/basic")
    public ResponseEntity<?> getPostBasic(
            @PathVariable("postId") Long postId
    ) {
        PostResult result = postService.getPostBasic(postId);

        return ResponseEntity.ok()
                .body(result);
    }

    /*
     * 게시글 조회 - 비관적 락 조회수 업데이트
     */
    @GetMapping("/posts/{postId}/pessimistic")
    public ResponseEntity<?> getPostWithPessimisticLock(
            @PathVariable("postId") Long postId
    ) {
        PostResult result = postService.getPostWithPessimisticLock(postId);

        return ResponseEntity.ok()
                .body(result);
    }

    /*
     * 게시글 조회 - 낙관적 락 조회수 업데이트
     */
    @GetMapping("/posts/{postId}/optimistic")
    public ResponseEntity<?> getPostWithOptimisticLock(
            @PathVariable("postId") Long postId
    ) {
        PostResult result = postService.getPostWithOptimisticLock(postId);

        return ResponseEntity.ok()
            .body(result);
    }

    /*
     * 게시글 조회 - 원자적 쿼리 조회수 업데이트
     */
    @GetMapping("/posts/{postId}/atomic")
    public ResponseEntity<?> getPostWithAtomic(
            @PathVariable("postId") Long postId
    ) {
        PostResult result = postService.getPostWithAtomic(postId);

        return ResponseEntity.ok()
                .body(result);
    }
}
