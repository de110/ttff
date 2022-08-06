package com.example.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String si;

    @Column
    private String gungu;

    @Column
    private String dong;

    @Builder
    public Region(String si, String gungu, String dong) {
        this.si = si;
        this.gungu = gungu;
        this.dong = dong;
    }
}
