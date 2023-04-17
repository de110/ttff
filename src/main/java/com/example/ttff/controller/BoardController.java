package com.example.ttff.controller;

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

import com.example.ttff.domain.Board;
import com.example.ttff.domain.ChatRoom;
import com.example.ttff.domain.Member;
import com.example.ttff.dto.BoardDto;
import com.example.ttff.repository.BoardRepository;
import com.example.ttff.repository.ChatRepository;
import com.example.ttff.repository.MemberRepository;
import com.example.ttff.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final BoardService boardService;
    private final ChatRepository chatRepository;

    // create post
    @PostMapping("/board")
    public Long board(@RequestBody Board board) {
        return boardService.createBoard(board).getId();
    }

    // search board by type
    // @GetMapping("/api/boards/{type}")
    // public List<Board> getBoardByType(@PathVariable("type") String type,
    // @AuthenticationPrincipal UserDetails user) {
    // User users = userRepository.findByUsername(user.getUsername()).get();
    // // return boardRepository.findByTypeAndUser(type, users);
    // return boardRepository.findByTypeAndRegion(type, users.getRegion());
    // }

    // get all post
    @GetMapping("/{type}")
    public List<Board> getAllBoard(@PathVariable String type) {
        // Member users = userRepository.findByMemberId(user.getUsername()).get();
        // log.info("username: {}", user.getUsername());
        // return boardRepository.findByUser(users);
        // return boardRepository.findByRegion(users.get());
        return boardRepository.findByType(type);
    }

    @GetMapping("/boards")
    public List<Board> getAllBoards(@RequestParam String dong) {
        // Member users = userRepository.findByMemberId(user.getUsername()).get();
        // log.info("username: {}", user.getUsername());
        // return boardRepository.findByUser(users);
        // return boardRepository.findByRegion(users.get());
        return boardRepository.findByDongNm(dong);
    }

    // select post by id
    @GetMapping(path = "/{type}", params = "id")
    public Board getBoardbyId(@PathVariable("type") String type, @RequestParam Long id) {
        Optional<Board> boards = boardRepository.findById(id);
        return boards.get();
    }

    @GetMapping(path = "/{type}", params = "dong")
    public List<Board> getBoardByType(@PathVariable String type, @RequestParam(required = false) String dong) {
        return boardRepository.findByTypeAndDongNm(type, dong);
    }

}
