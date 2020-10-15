package com.ellyspace.springcache.application;

import com.ellyspace.springcache.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) {
        log.info("...fetching Posts");
        findWithCache();
    }

    private void findWithCache() {
        log.info("...fetching Posts");
        log.info("1 ==>" + postRepository.findByIdWithCache(1L));
        log.info("2 ==>" + postRepository.findByIdWithCache(2L));
        log.info("1 ==>" + postRepository.findByIdWithCache(1L));
        log.info("2 ==>" + postRepository.findByIdWithCache(2L));
        log.info("1 ==>" + postRepository.findByIdWithCache(1L));
        log.info("2 ==>" + postRepository.findByIdWithCache(2L));
    }

    private void findWithoutCache() {
        log.info("1 ==>" + postRepository.findById(1L));
        log.info("2 ==>" + postRepository.findById(2L));
        log.info("1 ==>" + postRepository.findById(1L));
        log.info("2 ==>" + postRepository.findById(2L));
        log.info("1 ==>" + postRepository.findById(1L));
        log.info("2 ==>" + postRepository.findById(2L));
    }
}
