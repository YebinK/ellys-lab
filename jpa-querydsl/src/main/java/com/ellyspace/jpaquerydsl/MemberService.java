package com.ellyspace.jpaquerydsl;

import com.ellyspace.jpaquerydsl.repository.MemberRepository;
import org.springframework.stereotype.Service;

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
