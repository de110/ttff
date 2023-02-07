package com.example.ttff.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long roomId;

    // @Column
    // private String roomId;

    @Column
    private String roomName;

    // @Column
    // private String host;

    // @Column
    // private String guest;
    @ManyToOne
    @JoinColumn(name = "host")
    private Member host; // who made room

    @ManyToOne
    @JoinColumn(name = "guest")
    private Member guest;

    public static ChatRoom create(String name, Member host, Member guest) {
        ChatRoom room = new ChatRoom();
        // room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        room.host = host;
        room.guest = guest;
        return room;
    }

    @Builder
    public ChatRoom(String roomname, Member host, Member guest) {
        // this.roomId = roomId;
        this.roomName = roomname;
        this.host = host;
        this.guest = guest;
    }
}
