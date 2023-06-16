package com.example.ttff.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChatMessage {

    public enum MessageType {
        ENTER, TALK, JOIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private MessageType type;

    @Column
    private String roomId;
    // 보내는 사람
    // @Column
    // private String sender;

    @JoinColumn(name = "sender")
    @OneToOne
    private Member sender;

    // 내용
    @Column
    private String message;

    @Builder
    public ChatMessage(String roomId, Member sender, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

}
