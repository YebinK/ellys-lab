package com.ellyspace.jparelationships.onetomany.cascade;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_TEAM")
    private Team team;

    public Member() {
    }

    public Member(String name) {
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
