package com.example.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.domain.Board;
import com.example.user.domain.Regions;
import com.example.user.domain.User;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByType(String type);

    List<Board> findByUser(User user);

    List<Board> findByTypeAndUser(String type, User user);

    List<Board> findByTypeAndRegion(String type, Regions region);

    List<Board> findByRegion(Regions region);

    Optional<Board> findByTitle(String title);

}
