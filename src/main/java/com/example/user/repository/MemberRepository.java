package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
