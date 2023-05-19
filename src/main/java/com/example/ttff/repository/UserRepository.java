package com.example.ttff.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ttff.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByMemberId(String username);

    Boolean existsByMemberId(String memberId);
}