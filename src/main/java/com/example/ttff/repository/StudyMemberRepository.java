package com.example.ttff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ttff.domain.Member;
import com.example.ttff.domain.StudyMember;

public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {
    @Query(value = "SELECT distinct re.title FROM StudyMember re")
    public List<String> select();

    List<StudyMember> findByMember(Member member);

    Boolean existsByMember(Member member);

    // List<StudyMember> findDistinctByTitle(String name);
}
