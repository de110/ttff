package com.example.user.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.domain.ChatMessage;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomId(String roomId);
}