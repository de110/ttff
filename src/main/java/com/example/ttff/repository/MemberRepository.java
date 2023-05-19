package com.example.ttff.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ttff.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByMemberId(String username);

    Boolean existsByMemberId(String userId);
}