package com.benchpress200.viewcounter.repository;

import com.benchpress200.viewcounter.domain.PostWithVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostWithVersionRepository extends JpaRepository<PostWithVersion, Long> {
}
