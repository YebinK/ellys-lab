package com.ellyspace.jpatroubleshootings;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class CommentCreateRequest {

    private String content;
    private Long postId;
}
