package com.example.ttff.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.ttff.domain.Board;
import com.example.ttff.domain.ChatRoom;
import com.example.ttff.domain.User;
import com.example.ttff.repository.BoardRepository;
import com.example.ttff.repository.ChatRepository;
import com.example.ttff.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository memberRepository;
    private final ChatRepository chatRepository;

    // @Transactional
    // public void createBoard(Board board) {
    // boardRepository.save(board);
    // }

    @Transactional
    public Board createBoard(Board board) {

        // ChatRoom chatRoom = new ChatRoom();
        // chatRoom.setHost(user);
        // chatRepository.save(chatRoom);

        return boardRepository.save(board);
    }
}
