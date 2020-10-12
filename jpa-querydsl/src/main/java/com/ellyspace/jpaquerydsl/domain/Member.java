package com.ellyspace.jpaquerydsl.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    protected Member() {
    }

    public Member(String name) {
        this.name = name;
    }

    public void setTeam(Team team) {
        if (Objects.isNull(this.team)) {
            this.team = team;
        }
    }
}
