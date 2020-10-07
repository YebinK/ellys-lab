package com.ellyspace.jpaquerydsl;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void foo() {
        QMember qMember = QMember.member;

        memberRepository.findTop3By();

    }
}
