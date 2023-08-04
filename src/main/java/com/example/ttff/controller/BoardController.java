package com.example.ttff.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.dto.BoardDto;
import com.example.ttff.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 작성
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/board")
    public BoardDto.Res createPost(@RequestBody BoardDto board) {
        return boardService.createPost(board);
    }

    // 전체 게시글 조회
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/boards")
    public List<BoardDto.Res> getAllPosts() {
        return boardService.findAllPosts();
    }

    // 카테고리 기준 게시글 검색
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{type}")
    public List<BoardDto.Res> getPostsByCategory(@PathVariable String type) {
        return boardService.findByCategory(type);
    }

    // 게시글 선택
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/board/{id}")
    public BoardDto.Res getPostById(@PathVariable final Long id) {
        return boardService.findById(id);
    }

    // 게시글 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/board/{id}")
    public void deletePost(@PathVariable final Long id) {
        boardService.deletePost(id);
    }

}
