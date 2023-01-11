package com.example.user.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.user.domain.ChatMessage;
import com.example.user.domain.ChatRoom;
import com.example.user.domain.User;
import com.example.user.repository.ChatRepository;
import com.example.user.repository.MessageRepository;
import com.example.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    // private final UserRepository userRepository;
    // private Map<String, ChatRoom> chatRooms;

    // @PostConstruct
    // // 의존관게 주입완료되면 실행되는 코드
    // private void init() {
    // chatRooms = new LinkedHashMap<>();
    // }

    // 채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        // 채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = chatRepository.findAll();
        // Collections.reverse(result);
        // List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        // Collections.reverse(result);

        // return result;
        log.info("result: {}", result);
        return result;
    }

    // 채팅방 하나 불러오기
    public List<ChatRoom> findByRoomId(Long roomId) {
        return chatRepository.findByRoomId(roomId);
    }

    // @Transactional
    // public ChatRoom createRoom(String name, String host, String guest) {
    // ChatRoom chatRoom = ChatRoom.create(name, host, guest);
    // // return chatRoom;
    // // chatRooms.put(chatRoom.getRoomId(), chatRoom);

    // return chatRepository.save(chatRoom);

    // }

    @Transactional
    public ChatRoom save(String inviteUrl) {
        ChatRoom chatRoom = chatRepository.findById(1L).get();
        return chatRepository.save(chatRoom);
    }

    @Transactional
    public ChatRoom create(ChatRoom chatRoom) {
        chatRepository.save(chatRoom); // room 정보 저장
        return chatRoom;
    }

    @Transactional
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        // ChatMessage chatMessage = new ChatMessage();
        // chatRooms.put(chatRoom.getRoomId(), chatMessage);
        return messageRepository.save(chatMessage);
    }

    public List<ChatMessage> findAllMsgs() {
        List<ChatMessage> result = messageRepository.findAll();
        return result;
    }

    @Transactional
    public void deleteChatRoom(String roomId) {
        chatRepository.deleteByRoomId(roomId);
    }

}