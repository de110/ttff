package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.domain.Regions;

public interface RegionRepository extends JpaRepository<Regions,Long> {
    
}
