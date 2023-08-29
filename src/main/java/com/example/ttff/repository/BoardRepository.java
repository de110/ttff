package com.example.ttff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ttff.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByCategory(String category);

    Optional<Board> findByTitle(String title);

    List<Board> findByDongNm(String dongNm);

    void deleteById(Long id);

}
