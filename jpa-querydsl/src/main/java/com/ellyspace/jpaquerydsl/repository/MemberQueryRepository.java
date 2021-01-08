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

    /**
     * countDistinct() vs count() vs fetchCount()
     */
    public Long findMembersCountDistinct() {
        return jpaQueryFactory.select(member.id.countDistinct())
                .from(member)
                .fetchFirst();
    }

    public Long findMembersCount() {
        return jpaQueryFactory.select(member.id.count())
                .from(member)
                .fetchFirst();
    }

    public Long findMembersFetchCount() {
        return jpaQueryFactory.select(member.id)
                .from(member)
                .fetchCount();
    }

}
