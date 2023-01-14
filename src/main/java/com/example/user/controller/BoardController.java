package com.example.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.domain.Board;
import com.example.user.domain.ChatRoom;
import com.example.user.domain.User;
import com.example.user.dto.BoardDTO;
import com.example.user.repository.BoardRepository;
import com.example.user.repository.ChatRepository;
import com.example.user.repository.UserRepository;
import com.example.user.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardService boardService;
    private final ChatRepository chatRepository;

    // create post
    @PostMapping("/api/board")
    public Board board(@RequestBody Board board, @AuthenticationPrincipal UserDetails user) {
        return boardService.createBoard(board, user.getUsername());
    }

    // search board by type
    @GetMapping("/api/boards/{type}")
    public List<Board> getBoardByType(@PathVariable("type") String type, @AuthenticationPrincipal UserDetails user) {
        User users = userRepository.findByUsername(user.getUsername()).get();
        // return boardRepository.findByTypeAndUser(type, users);
        return boardRepository.findByTypeAndRegion(type, users.getRegion());
    }

    // get all post
    @GetMapping("/api/boards")
    public List<Board> getAllBoard(@AuthenticationPrincipal UserDetails user) {
        // List<Board> board = new ArrayList<Board>();
        // board = boardRepository.findAll();

        User users = userRepository.findByUsername(user.getUsername()).get();
        // log.info("username: {}", user.getUsername());
        // return boardRepository.findByUser(users);
        return boardRepository.findByRegion(users.getRegion());
        // return board;
    }

    // select post by id
    @GetMapping("/{type}")
    public Board getBoardbyId(@PathVariable("type") String type, @RequestParam Long id) {
        Optional<Board> boards = boardRepository.findById(id);
        return boards.get();
    }

}
