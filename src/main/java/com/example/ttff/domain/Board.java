package com.example.ttff.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String rule;

    @Column
    private String type;

    @Column
    private String start;

    @Column
    private String end;

    @Column
    String member;

    // @OneToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "REGION_ID")
    // private Regions region;
    @Column
    private String dongNm;

    @Builder
    public Board(String title, String rule, String type, String start, String end,
            String member, String dongNm) {
        this.title = title;
        this.rule = rule;
        this.type = type;
        this.start = start;
        this.end = end;
        this.member = member;
        this.dongNm = dongNm;
    }

}
