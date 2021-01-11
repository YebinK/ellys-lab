package com.ellyspace.jpaquerydsl.repository;

import com.ellyspace.jpaquerydsl.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * countDistinct() vs count() vs fetchCount() 테스트
 */
@SpringBootTest
class MemberQueryRepositoryTest {

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.save(new Member("elly1"));
        memberRepository.save(new Member("elly2"));
        memberRepository.save(new Member("elly3"));
        memberRepository.save(new Member("elly4"));
        memberRepository.save(new Member("elly5"));
        memberRepository.save(new Member("elly6"));
        memberRepository.save(new Member("elly7"));
        memberRepository.save(new Member("elly8"));
        memberRepository.save(new Member("elly9"));
        memberRepository.save(new Member("elly5"));
    }

    @Test
    void findMembersCountDistinct() {
        System.err.println("========countDistinct 테스트 시작");
        Long memberSize = memberQueryRepository.findMembersCountDistinct();
        assertThat(memberSize).isEqualTo(10);
        System.err.println("========countDistinct 테스트 종료");
    }

    @Test
    void findMembersCount() {
        System.err.println("========count 테스트 시작");
        Long memberSize = memberQueryRepository.findMembersCount();
        assertThat(memberSize).isEqualTo(10);
        System.err.println("========count 테스트 종료");
    }

    @Test
    void findMembersFetchCount() {
        System.err.println("========fetchCount 테스트 시작");
        Long memberSize = memberQueryRepository.findMembersFetchCount();
        assertThat(memberSize).isEqualTo(10);
        System.err.println("========fetchCount 테스트 종료");
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }
}