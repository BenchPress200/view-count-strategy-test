package com.benchpress200.viewcounter.repository;

import com.benchpress200.viewcounter.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
