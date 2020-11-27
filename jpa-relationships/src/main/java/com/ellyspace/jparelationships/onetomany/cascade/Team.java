package com.ellyspace.jparelationships.onetomany.cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Member> members = new ArrayList<>();

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }
}
