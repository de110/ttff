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

@Entity
@Getter
@NoArgsConstructor
public class Attend extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Member member;

    @Column
    private String attend;

    @JoinColumn
    @ManyToOne
    private Study study;

    @Builder
    public Attend(Member member, String attend, Study study) {
        this.member = member;
        this.attend = attend;
        this.study = study;
    }
}
