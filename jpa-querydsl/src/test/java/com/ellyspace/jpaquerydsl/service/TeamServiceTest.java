package com.ellyspace.jpaquerydsl.service;

import com.ellyspace.jpaquerydsl.domain.Member;
import com.ellyspace.jpaquerydsl.domain.Team;
import com.ellyspace.jpaquerydsl.repository.TeamRepository;
import com.ellyspace.jpaquerydsl.service.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("페이징 단독 사용 - limit 쿼리 나감")
    @Test
    void paging() {
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
        List<Team> persistTeams = teamService.findThreeTeamsWithoutMember();

        //then
        assertThat(persistTeams).hasSize(3);
    }

    @DisplayName("페이징과 컬렉션 페치조인 동시 사용, 컬렉션이 없는 경우 - 문제 없지만 limit 쿼리 안 나감")
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
        List<Team> persistTeams = teamService.findThreeTeams();

        //then
        assertThat(persistTeams).hasSize(3);
    }

    @DisplayName("페이징과 컬렉션 페치조인 동시 사용, 컬렉션 값이 있는 경우 - 문제 없지만 limit 쿼리 안 나감")
    @Test
    void fetchJoinWithPagingWhenLargeData() {
        List<Team> createdTeams = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String teamName = "팀" + i;
            Team team = new Team(teamName);
            team.addMember(new Member("멤버"));
            team.addMember(new Member("멤버"));
            createdTeams.add(team);
        }

        teamRepository.saveAll(createdTeams);

        //when
        List<Team> persistTeams = teamService.findThreeTeams();

        //then
        assertThat(persistTeams).hasSize(3);
    }

}
