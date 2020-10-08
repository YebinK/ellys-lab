package com.ellyspace.jpaquerydsl.repository;

import com.ellyspace.jpaquerydsl.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findTop3By();

}
