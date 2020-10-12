package com.ellyspace.jpaquerydsl.service;

import com.ellyspace.jpaquerydsl.domain.Team;
import com.ellyspace.jpaquerydsl.repository.TeamQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamQueryRepository teamQueryRepository;

    @Transactional
    public List<Team> findThreeTeams() {
        return teamQueryRepository.findThreeTeams();
    }
}
