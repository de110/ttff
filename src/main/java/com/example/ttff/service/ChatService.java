package com.example.ttff.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.ttff.domain.Board;
import com.example.ttff.domain.ChatMessage;
import com.example.ttff.domain.ChatRoom;
import com.example.ttff.domain.Member;
import com.example.ttff.repository.BoardRepository;
import com.example.ttff.repository.ChatRepository;
import com.example.ttff.repository.MessageRepository;
import com.example.ttff.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final MemberRepository userRepository;
    private final BoardRepository boardRepository;

    // 채팅방 불러오기
    public List<ChatRoom> findUserChatRooms(String username) {
        // 채팅방 최근 생성 순으로 반환
        Member member = userRepository.findByMemberId(username).get();
        return chatRepository.findByHostOrGuest(member, member);

    }

    // 채팅방 하나 불러오기
    public List<ChatRoom> findByRoomId(Long roomId) {
        return chatRepository.findByRoomId(roomId);
    }

    @Transactional
    public ChatRoom save(String inviteUrl) {
        ChatRoom chatRoom = chatRepository.findById(1L).get();
        return chatRepository.save(chatRoom);
    }

    @Transactional
    public ChatRoom create(ChatRoom chatRoom) {
        validateDuplicateChatRoom(chatRoom);
        // chatRoom.setGuest(chatRoom.getGuest());
        chatRepository.save(chatRoom);
        return chatRoom;
    }

    // 채팅방 중복 검사
    public void validateDuplicateChatRoom(ChatRoom chatRoom) {
        Boolean findChatRoom = chatRepository.existsByRoomName(chatRoom.getRoomName());
        log.info("find: {}", findChatRoom);
        if (findChatRoom == true) {
            throw new IllegalStateException("exist");
        }
    }

    @Transactional
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        // ChatMessage chatMessage = new ChatMessage();
        // chatRooms.put(chatRoom.getRoomId(), chatMessage);
        String username = chatMessage.getSender().getMemberId();
        Member member = userRepository.findByMemberId(username).get();
        chatMessage.setSender(member);
        // chatMessage.setRoomId(chatMessage.getRoomId());
        log.info("msg: {}", chatMessage);
        return messageRepository.save(chatMessage);
    }

    public List<ChatMessage> findAllMsgs() {
        List<ChatMessage> result = messageRepository.findAll();
        return result;
    }

    @Transactional
    public void deleteChatRoom(Long roomId) {
        chatRepository.deleteByRoomId(roomId);
    }

}