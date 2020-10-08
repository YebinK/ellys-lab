package com.ellyspace.jpaquerydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TeamQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public TeamQueryRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


}
