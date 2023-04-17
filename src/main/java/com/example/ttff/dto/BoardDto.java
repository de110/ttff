package com.example.ttff.dto;

import com.example.ttff.domain.Board;
import com.example.ttff.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
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
