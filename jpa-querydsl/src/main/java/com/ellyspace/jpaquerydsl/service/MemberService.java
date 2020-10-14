package com.ellyspace.jpaquerydsl.service;

import com.ellyspace.jpaquerydsl.domain.Member;
import com.ellyspace.jpaquerydsl.repository.MemberQueryRepository;
import com.querydsl.core.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Transactional
    public List<Member> findMembersByTeam(Long teamId) {
        QueryResults<Member> members = memberQueryRepository.findMembersByTeam(teamId);
        return members.getResults();
    }
}
