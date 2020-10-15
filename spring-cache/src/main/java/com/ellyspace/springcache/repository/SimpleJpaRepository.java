package com.ellyspace.springcache.repository;

import com.ellyspace.springcache.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class SimpleJpaRepository implements PostRepository {
    @Override
    public Post findById(Long id) {
        simulateSlowService();

        return new Post(id, "found");
    }

    private void simulateSlowService() {
        try{
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
