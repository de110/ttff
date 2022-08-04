package com.example.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.domain.Board;
import com.example.user.domain.User;
import com.example.user.dto.BoardDTO;
import com.example.user.repository.BoardRepository;
import com.example.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;
    private final HttpSession session;
    private final UserRepository userRepository;

    @GetMapping("/boards")
    public List<Board> getBoards() {
        // Board board = boardDTO.toEntity();
        return boardRepository.findAll();
    }

    @PostMapping("/board")
    public Board board(@RequestBody Board board) {
        Optional<User> user = userRepository.findById(1L);
        // board =
        // Board.builder().title("title1").contents("contents1").user(user.get()).build();
        board = Board.builder().title(board.getTitle()).rule(board.getRule()).type(board.getType())
                .start(board.getStart()).end(board.getEnd()).user(user.get()).build();
        return boardRepository.save(board);
    }

    // 카테고리로 검색
    @GetMapping("/api/{type}")
    public List<Board> getBoardbytype(@PathVariable("type") String type) {
        List<Board> board = boardRepository.findByType(type);
        return board;

    }

    @GetMapping("/{type}")
    public Board getBoardbyId(@PathVariable("type") String type, @RequestParam Long id) {
        Optional<Board> boardid = boardRepository.findById(id);
        Board board = new Board();
        if (boardid.get().getType().equals(type)) {
            board = boardid.get();
            // return true;
        }
        
            return board;

    }
    
}
