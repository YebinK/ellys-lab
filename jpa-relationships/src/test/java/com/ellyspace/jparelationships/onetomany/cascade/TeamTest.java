package com.ellyspace.jparelationships.onetomany.cascade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TeamTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("cascadeType.ALL일 때")
    @Test
    void addMember() {
        Team team = new Team("team1");
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        Member member3 = new Member("member3");

        team.addMember(member1);
        team.addMember(member2);
        team.addMember(member3);

        teamRepository.save(team);

        assertThat(memberRepository.findAll()).hasSize(3);

        //궁금한 것. @JoinColumn을 member쪽에 작성하는 것과 team 쪽에 작성하는 것 어떻게 다를까?
    }
}