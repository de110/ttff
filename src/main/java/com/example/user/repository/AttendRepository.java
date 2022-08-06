package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.domain.Attend;

public interface AttendRepository extends JpaRepository<Attend, Long>
{
    
}
