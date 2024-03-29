package com.example.ttff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ttff.domain.Attend;

public interface AttendRepository extends JpaRepository<Attend, Long> {
    List<Attend> findByStudy(String study);

    List<Attend> findByMemberId(String memberId);
}
