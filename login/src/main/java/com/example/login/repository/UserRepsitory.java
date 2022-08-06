package com.example.login.repository;

import com.example.login.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepsitory extends JpaRepository<User, Long> {

}
