package com.ellyspace.jpaquerydsl;

import com.ellyspace.jpaquerydsl.domain.Team;
import com.ellyspace.jpaquerydsl.repository.TeamRepository;
import com.ellyspace.jpaquerydsl.service.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("컬렉션 페치조인과 페이징 동시 사용 - 문제 없음")
    @Test
    void fetchJoinWithPaging() {
        //given
        Team team1 = new Team("팀1");
        Team team2 = new Team("팀2");
        Team team3 = new Team("팀3");
        Team team4 = new Team("팀4");
        Team team5 = new Team("팀5");
        Team team6 = new Team("팀6");
        Team team7 = new Team("팀7");

        teamRepository.saveAll(Arrays.asList(team1, team2, team3, team4, team5, team6, team7));

        //when
        List<Team> teams = teamService.findThreeTeams();

        //then
        assertThat(teams).hasSize(3);
    }
}
