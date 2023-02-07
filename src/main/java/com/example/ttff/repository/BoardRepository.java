package com.example.ttff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ttff.domain.Board;
import com.example.ttff.domain.Member;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByType(String type);

    List<Board> findByMember(Member member);

    List<Board> findByTypeAndMember(String type, Member member);

    List<Board> findByTypeAndDongNm(String type, String DongNm);

    List<Board> findByDongNm(String dongNm);

    Optional<Board> findByTitle(String title);

}
