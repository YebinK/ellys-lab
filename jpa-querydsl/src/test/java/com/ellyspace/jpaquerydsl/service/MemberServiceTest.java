package com.ellyspace.jpaquerydsl.service;

import com.ellyspace.jpaquerydsl.domain.Member;
import com.ellyspace.jpaquerydsl.domain.Team;
import com.ellyspace.jpaquerydsl.repository.TeamQueryRepository;
import com.ellyspace.jpaquerydsl.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private TeamQueryRepository teamQueryRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberService memberService;

    @DisplayName("페이징 + 페치조인 문제를 막기 위해 Member에 직접 접근한다.")
    @Test
    void findMembersDirectly() {
        //given
        Team team1 = new Team("팀1");
        team1.addMember(new Member("멤버1"));
        team1.addMember(new Member("멤버2"));
        team1.addMember(new Member("멤버3"));

        Team team2 = new Team("팀1");
        team2.addMember(new Member("멤버4"));
        team2.addMember(new Member("멤버5"));
        team2.addMember(new Member("멤버6"));
        team2.addMember(new Member("멤버7"));

        teamRepository.save(team1);
        teamRepository.save(team2);

        //when
        List<Member> members = memberService.findMembersByTeam(team2.getId());

        assertThat(members).hasSize(3);
        assertThat(members.get(0).getName()).isEqualTo("멤버4");
        assertThat(members.get(1).getName()).isEqualTo("멤버5");
        assertThat(members.get(2).getName()).isEqualTo("멤버6");
    }
}
