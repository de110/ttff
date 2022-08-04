package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.domain.domain;

public interface DomainRepository extends JpaRepository<domain,Long> {
    
}
