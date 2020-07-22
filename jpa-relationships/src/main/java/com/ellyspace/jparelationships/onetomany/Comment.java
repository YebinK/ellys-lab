package com.ellyspace.jparelationships.onetomany;

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
    public Comment(Long id, String content, Post post) {
        this.id = id;
        this.content = content;
        setPost(post);
    }

    private void setPost(Post post) {
        if (Objects.isNull(this.post)) {
            this.post = post;
            this.post.getComments().add(this);
        }
    }
}
