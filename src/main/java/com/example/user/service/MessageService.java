package com.example.user.service;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.domain.ChatMessage;
import com.example.user.domain.ChatRoom;
import com.example.user.repository.MessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    // 의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 채팅방 메시지 불러오기
    public List<ChatMessage> findByRoomId(String roomId) {
        return messageRepository.findByRoomId(roomId);
    }
}