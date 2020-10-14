package com.ellyspace.jpaquerydsl.repository;

import com.ellyspace.jpaquerydsl.domain.Member;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.ellyspace.jpaquerydsl.domain.QMember.member;

@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public MemberQueryRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public QueryResults<Member> findMembersByTeam(Long teamId) {
        return jpaQueryFactory.selectFrom(member)
                .where(member.team.id.eq(teamId))
                .limit(3)
                .fetchResults();
    }
}
