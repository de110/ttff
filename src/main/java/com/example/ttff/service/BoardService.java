package com.example.ttff.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ttff.config.SecurityUtil;
import com.example.ttff.domain.Board;
import com.example.ttff.domain.Member;
import com.example.ttff.dto.BoardDto;
import com.example.ttff.repository.BoardRepository;
import com.example.ttff.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Member isMemberCurrent() {
        return memberRepository.findByMemberId(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("there is no user information"));
    }

    // 게시글 작성
    @Transactional
    public BoardDto.Res createPost(BoardDto boardDto) {
        Member member = isMemberCurrent();
        Board board = Board.createPost(boardDto, member);
        boardRepository.save(board);
        return new BoardDto.Res(board);
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

    // 지역 기준 게시글 조회
    @Transactional(readOnly = true)
    public List<BoardDto.Res> findByRegion(String dongNm) {
        List<Board> boards = boardRepository.findByDongNm(dongNm);
        List<BoardDto.Res> boardDtos = new ArrayList<>();
        boards.forEach(board -> boardDtos.add(new BoardDto.Res(board)));
        return boardDtos;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
