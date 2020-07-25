package com.ellyspace.jpatroubleshootings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void createComment(CommentCreateRequest request) {
        Post persistPost = postRepository.findById(request.getPostId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Post입니다."));
        Comment comment = Comment.builder()
            .post(persistPost)
            .content(request.getContent())
            .build();

        postRepository.save(persistPost);
    }

}
