package com.example.ttff.dto;

import com.example.ttff.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDTO {
    private String title;
    private String rule;

    public Board toEntity() {
        Board board = Board.builder()
                .title(title)
                .rule(rule)
                .build();
        return board;
    }

}
