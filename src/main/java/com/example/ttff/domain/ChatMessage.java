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

@Setter
@Getter
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

    @JoinColumn(name = "sender")
    @OneToOne
    private Member member;

    @Column
    private String message;

    @Builder
    public ChatMessage(String roomId, Member member, String message) {
        this.roomId = roomId;
        this.member = member;
        this.message = message;
    }

}
