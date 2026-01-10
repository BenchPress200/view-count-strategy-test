package com.benchpress200.viewcounter.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "view_count", nullable = false)
    private Long viewCount;

    private Post(String title) {
        this.title = title;
        this.viewCount = 0L;
    }

    public static Post of(String title) {
        return new Post(title);
    }

    public void incrementViewCount() {
        viewCount++;
    }
}
