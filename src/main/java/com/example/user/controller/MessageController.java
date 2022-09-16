package com.example.user.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.domain.ChatMessage;
import com.example.user.service.ChatService;
import com.example.user.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    private final ChatService chatService;
    private final MessageService messageService;

    @PostMapping("/chat/message")
    @MessageMapping("/chat/message")
    public ChatMessage enter(@RequestBody ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        }
        sendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(),
                message);

        return chatService.saveMessage(message);
        // ChatMessage chatMessage = new ChatMessage();
        // return message;

    }

    // 모든 채팅 내역 반환
    @GetMapping("/chat/allmessages")
    public List<ChatMessage> allMsgs() {
        return chatService.findAllMsgs();
    }

    // 특정 채팅방 채팅 내역 조회
    @GetMapping("/messages")
    public List<ChatMessage> getRoomParam(@RequestParam String roomId) {
        return messageService.findByRoomId(roomId);
    }

}