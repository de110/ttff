package com.example.ttff.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.Board;
import com.example.ttff.repository.BoardRepository;
import com.example.ttff.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    // create post
    @PostMapping("/board")
    public Long board(@RequestBody Board board) {
        return boardService.createBoard(board).getId();
    }

    // get all post
    @GetMapping("/{type}")
    public List<Board> getAllBoard(@PathVariable String type) {
        return boardRepository.findByType(type);
    }

    @GetMapping("/boards")
    public List<Board> getAllBoards(@RequestParam String dong) {
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
