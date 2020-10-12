package com.ellyspace.jpaquerydsl.repository;

import com.ellyspace.jpaquerydsl.domain.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ellyspace.jpaquerydsl.domain.QTeam.team;

@Repository
public class TeamQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public TeamQueryRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    //페이징 API와 컬렉션 페치조인 함께 사용
    public List<Team> findThreeTeams() {
        return jpaQueryFactory.selectFrom(team)
                .leftJoin(team.members).fetchJoin()
                .limit(3)
                .fetch();
    }
}
