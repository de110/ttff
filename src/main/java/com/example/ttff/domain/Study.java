package com.example.ttff.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMEMBER_ID")
    private Long id;

    @JoinColumn(name = "MEMBER_ID")
    @ManyToOne
    private Member member;

    @Column(name = "title")
    private String title;

    @Builder
    public Study(Member member, String title, Boolean manager) {
        this.member = member;
        this.title = title;
    }
}
