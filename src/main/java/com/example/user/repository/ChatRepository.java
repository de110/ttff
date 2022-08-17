package com.example.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.domain.ChatRoom;

public interface ChatRepository extends JpaRepository<ChatRoom,Long>{
    @Override
    List<ChatRoom> findAll();

    List<ChatRoom> findByRoomId(Long roomId);

    long deleteByRoomId(String roomId);
}
