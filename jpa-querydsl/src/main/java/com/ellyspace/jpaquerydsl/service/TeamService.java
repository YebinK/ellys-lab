package com.ellyspace.jpaquerydsl.service;

import com.ellyspace.jpaquerydsl.domain.Team;
import com.ellyspace.jpaquerydsl.repository.TeamQueryRepository;
import com.querydsl.core.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamQueryRepository teamQueryRepository;

    @Transactional
    public List<Team> findThreeTeamsWithoutMember() {
        QueryResults<Team> pagingResults = teamQueryRepository.findThreeTeamsWithoutMember();

        System.err.println("total: " + pagingResults.getTotal());
        System.err.println("limit: " + pagingResults.getLimit());
        System.err.println("offset: " + pagingResults.getOffset());
        return pagingResults.getResults();
    }

    @Transactional
    public List<Team> findThreeTeams() {
        List<Team> threeTeams = teamQueryRepository.findThreeTeams();

        threeTeams.stream().map(Team::getMembers)
                .flatMap(Collection::stream)
                .forEach(member -> System.out.println(member.getName()));

        return threeTeams;
    }
}
