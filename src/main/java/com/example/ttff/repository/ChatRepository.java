package com.example.ttff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ttff.domain.ChatRoom;
import com.example.ttff.domain.Member;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
    @Override
    List<ChatRoom> findAll();

    List<ChatRoom> findByRoomId(Long roomId);

    long deleteByRoomId(Long roomId);

    boolean existsByRoomName(String roomname);

    List<ChatRoom> findByHostOrGuest(Member host, Member Guest);
}
