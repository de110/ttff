package com.example.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REGION_ID")
    private Regions region;

    @Builder
    public Board(String title, String rule, String type, String start, String end,
            User user, Regions region) {
        this.title = title;
        this.rule = rule;
        this.type = type;
        this.start = start;
        this.end = end;
        this.user = user;
        this.region = region;
    }

}
