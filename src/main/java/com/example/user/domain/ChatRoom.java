package com.example.user.domain;


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
    @Column(name="ROOM_ID")
    private Long roomId;

    // @Column
    // private String roomId;

    @Column
    private String roomname;

    // @Column
    // private String host;

    // @Column
    // private String guest;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user; // who made room
    @Column
    private String inviteUrl;

    public static ChatRoom create(String name,String inviteUrl, User user) {
        ChatRoom room = new ChatRoom();
        // room.roomId = UUID.randomUUID().toString();
        room.roomname = name;
        room.user = user;
        // room.host = host;
        // room.guest = guest;
        room.inviteUrl = inviteUrl;
        return room;
    }

    @Builder
    public ChatRoom(String roomname, String inviteUrl,User user) {
        // this.roomId = roomId;
        this.roomname = roomname;
        this.user = user;
        // this.host = host;
        // this.guest = guest;
        this.inviteUrl = inviteUrl;

    }
}
