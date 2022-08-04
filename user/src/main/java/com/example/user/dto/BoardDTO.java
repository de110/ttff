package com.example.user.dto;

import com.example.user.domain.Board;
import com.example.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private String title;
    private String rule;
    // private User user;

    public Board toEntity() {
        Board board = Board.builder()
                .title(title)
                .rule(rule)
                // .user(user)
                .build();
        return board;
    }

}
