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
public class Regions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dong_cd;

    @Column
    private String sido_nm;

    @Column
    private String sigungu_nm;

    @Column
    private String dong_nm;

    @Column
    private Long dong_cd2;

    @Column
    private String dong_nm2;

    @Column
    private String base_year;


    @Builder
    public Regions(String sido_nm, String sigungu_nm, String dong_nm) {
        this.sido_nm = sido_nm;
        this.sigungu_nm = sigungu_nm;
        this.dong_nm = dong_nm;
    }
}
