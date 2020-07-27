package com.ellyspace.jpatroubleshootings;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Post post;

    @BeforeEach
    void setUp() {
        post = Post.builder()
            .content("hello")
            .build();

        postRepository.save(post);
    }

    @DisplayName("글에 댓글을 추가한다.")
    @Test
    void createComment() {
        //댓글 생성 request
        CommentCreateRequest request = CommentCreateRequest.builder()
            .content("world")
            .postId(post.getId())
            .build();

        commentService.createComment(request);
        Post persistPost = postRepository.findById(post.getId()).get();

        assertThat(persistPost.getComments()).hasSize(1);

    }
}