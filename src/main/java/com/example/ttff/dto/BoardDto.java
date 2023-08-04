package com.example.ttff.dto;

import com.example.ttff.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardDto {
    private String title;
    private String content;
    private String category;
    private String startDate;
    private String endDate;

    @Builder
    public BoardDto(String title, String content, String category, String startDate,
            String endDate) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    @Getter
    public static class Res {
        private String title;
        private String content;
        private String category;
        private String writer;

        public Res(Board board) {
            this.title = board.getTitle();
            this.content = board.getContent();
            this.writer = board.getMember().getMemberId();
        }

    }

}
