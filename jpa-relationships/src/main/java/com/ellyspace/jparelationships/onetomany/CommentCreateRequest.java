package com.ellyspace.jparelationships.onetomany;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor()
public class CommentCreateRequest {
    private String content;
    private Long postId;
}
