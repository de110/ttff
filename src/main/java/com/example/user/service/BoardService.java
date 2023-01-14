package com.example.user.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.user.domain.Board;
import com.example.user.domain.User;
import com.example.user.repository.BoardRepository;
import com.example.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // @Transactional
    // public void createBoard(Board board) {
    // Optional<User> user = userRepository.findById();

    // boardRepository.save(board);

    // }
    @Transactional
    public Board createBoard(Board board, String username) {
        User user = userRepository.findByUsername(username).get();
        board.setUser(user);
        board.setRegion(user.getRegion());
        return boardRepository.save(board);
    }
}
