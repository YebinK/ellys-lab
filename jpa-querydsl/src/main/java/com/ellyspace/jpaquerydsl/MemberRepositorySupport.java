package com.ellyspace.jpaquerydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public MemberRepositorySupport(JPAQueryFactory queryFactory) {
        super(Member.class);
        this.queryFactory = queryFactory;
    }

    public List<Member> foo() {
        QMember qMember = QMember.member;
        return queryFactory.selectFrom(qMember)
            .where(qMember.age.between(10, 30))
            .fetch();
    }
}
