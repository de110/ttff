package com.example.ttff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ttff.domain.Board;
import com.example.ttff.domain.Member;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByCategory(String category);

    List<Board> findByMember(Member member);

    List<Board> findByCategoryAndMember(String type, Member member);

    // List<Board> findByCategoryAnd(String type, String DongNm);

    // List<Board> findByDongNm(String dongNm);

    Optional<Board> findByTitle(String title);

}
