package com.ellyspace.springcache.repository;

import com.ellyspace.springcache.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository {
    Post findById(Long id);

    Post findByIdWithCache(Long id);
}
