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
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StudyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMEMBER_ID")
    private Long id;

    @JoinColumn(name = "MEMBER_ID")
    @ManyToOne
    User member;

    @Column(name = "title")
    private String title;

    @Column(name = "manager")
    private Boolean manager;

    @Builder
    public StudyMember(User member, String title, Boolean manager) {
        this.member = member;
        this.title = title;
        this.manager = manager;
    }
}
