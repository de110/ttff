package com.example.ttff.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.ttff.dto.BoardDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String category;

    @Column
    private String startDate;

    @Column
    private String endDate;

    @ManyToOne
    @JoinColumn
    private Member member;

    private String dongNm;

    @Builder
    public Board(String title, String content, String category, String startDate,
            String endDate, Member member, String dongNm) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.dongNm = dongNm;
    }

    // 게시글 생성
    public static Board createPost(BoardDto board, Member member) {
        return Board.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .member(member)
                .dongNm(member.getRegion().getDongNm())
                .build();
    }

}
