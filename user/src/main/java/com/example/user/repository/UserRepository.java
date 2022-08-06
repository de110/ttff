package com.example.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);
}
