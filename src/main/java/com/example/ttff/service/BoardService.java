package com.example.ttff.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ttff.domain.Board;
import com.example.ttff.dto.BoardDto;
import com.example.ttff.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 작성
    @Transactional
    public BoardDto.Res createPost(BoardDto boardDto) {
        return new BoardDto.Res(boardRepository.save(boardDto.toEntity()));
    }

    // 개별 게시글 조회
    @Transactional(readOnly = true)
    public BoardDto.Res findById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        return new BoardDto.Res(board.get());
    }

    // 전체 게시글 조회
    @Transactional(readOnly = true)
    public List<BoardDto.Res> findAllPosts() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto.Res> boardDtos = new ArrayList<>();
        boards.forEach(board -> boardDtos.add(new BoardDto.Res(board)));
        return boardDtos;
    }

    // 카테고리 기준으로 게시글 조회
    @Transactional(readOnly = true)
    public List<BoardDto.Res> findByCategory(String type) {
        List<Board> boards = boardRepository.findByCategory(type);
        List<BoardDto.Res> boardDtos = new ArrayList<>();
        boards.forEach(board -> boardDtos.add(new BoardDto.Res(board)));
        return boardDtos;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
