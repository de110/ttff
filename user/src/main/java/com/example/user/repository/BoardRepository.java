package com.example.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByType(String type);
}
