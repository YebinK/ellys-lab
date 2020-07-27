package com.ellyspace.jpatroubleshootings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN FETCH p.comments WHERE p.id = :postId")
    void fetchPost(@Param("postId") Long postId);
}
