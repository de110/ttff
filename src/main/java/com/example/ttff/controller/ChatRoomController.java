package com.example.ttff.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import com.example.ttff.domain.ChatRoom;
import com.example.ttff.domain.Member;
import com.example.ttff.repository.ChatRepository;
import com.example.ttff.repository.MemberRepository;
import com.example.ttff.service.ChatService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
// @RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    // 유저가 속한 모든 채팅방 목록 반환
    @GetMapping("/chat/rooms")
    public List<ChatRoom> findChatRoom(@RequestParam String userId) {
        return chatService.findUserChatRooms(userId);
    }

    @GetMapping("/chat/room")
    public List<ChatRoom> roomname(@RequestParam(value = "id") Long id) {
        return chatService.findByRoomId(id);
    }

    // 채팅방 생성
    @PostMapping("/api/chat/room")
    public ChatRoom create(ChatRoom chatRoom) {
        return chatService.create(chatRoom);
    }

    // RoomId 로 특정 채팅방 조회
    @GetMapping("/roominfo")
    public List<ChatRoom> getRoomParam(@RequestParam Long roomId) {
        return chatService.findByRoomId(roomId);
    }

    // 채팅방 삭제
    @DeleteMapping("/chat")
    public void deleteChatRoom(@RequestParam Long roomId) {
        chatService.deleteChatRoom(roomId);
    }

    // @GetMapping("/room/enter/{roomId}")
    // public String roomDetail(@PathVariable String roomId) {
    // // model.addAttribute("roomId", roomId);
    // return roomId;
    // }

    @GetMapping("/chat")
    public List<ChatRoom> roomDetail(@RequestParam Long roomId) {
        return chatService.findByRoomId(roomId);
    }
}