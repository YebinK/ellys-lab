package com.ellyspace.jpatroubleshootings;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Post post;

    @Builder
    public Comment(String content, Post post) {
        this.content = content;
        setPost(post); //연관관계 설정
    }

    //연관관계 편의 메소드
    private void setPost(Post post) {
        if (Objects.isNull(this.post)) {
            this.post = post;
            this.post.addComment(this);
        }
    }
}
