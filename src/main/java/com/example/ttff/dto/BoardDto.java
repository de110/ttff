package com.example.ttff.dto;

import com.example.ttff.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDto {
    private String title;
    private String content;

    public Board toEntity() {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .build();
        return board;
    }

}
