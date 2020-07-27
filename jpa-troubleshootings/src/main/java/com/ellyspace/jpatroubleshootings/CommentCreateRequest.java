package com.ellyspace.jpatroubleshootings;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {

    private String content;
    private Long postId;

    @Builder
    public CommentCreateRequest(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }
}
