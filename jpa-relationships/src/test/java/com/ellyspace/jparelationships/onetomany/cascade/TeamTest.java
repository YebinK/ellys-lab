package com.ellyspace.jparelationships.onetomany.cascade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TeamTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("oneToMany 단방향일 때 FK가 null로 insert된 후에 update가 일어난다.")
    @Test
    void oneToManyWithJoinColumn() {
//        Team team = new Team("team1");
//        Member member1 = new Member("member1");
//        Member member2 = new Member("member2");
//        Member member3 = new Member("member3");
//
//        team.addMember(member1);
//        team.addMember(member2);
//        team.addMember(member3);
//
//        teamRepository.save(team);
//
//        assertThat(memberRepository.findAll()).hasSize(3);
    }

    @DisplayName("manyToOne 단방향일 때 ~~~~~~~~~~~~~")
    @Transactional
    @Test
    void manyToOneWithJoinColumn() {
        Team team = new Team("team1");
        teamRepository.save(team);

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        Member member3 = new Member("member3");

        member1.setTeam(team);
        member2.setTeam(team);
        member3.setTeam(team);

        memberRepository.saveAll(Arrays.asList(member1, member2, member3));

        List<Member> savedMembers = memberRepository.findAll();
        assertThat(savedMembers).hasSize(3);
        assertThat(savedMembers.get(0).getTeam()).isEqualTo(team);
    }
}