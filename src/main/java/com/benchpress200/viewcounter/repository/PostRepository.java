package com.benchpress200.viewcounter.repository;

import com.benchpress200.viewcounter.domain.Post;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
           SELECT p
           FROM Post p
           WHERE p.id = :id
           """)
    Optional<Post> findByIdWithPessimisticLock(Long id);

    @Modifying
    @Query("""
            UPDATE Post p
            SET p.viewCount = p.viewCount + 1
            WHERE p.id = :id
            """)
    void incrementViewCount(Long id);
}
