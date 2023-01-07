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
    @Column(name = "dong_cd")
    private Long dongCd;

    @Column(name = "sido_nm")
    private String sidoNm;

    @Column(name = "sigungu_nm")
    private String sigunguNm;

    @Column(name = "dong_nm")
    private String dongNm;

    @Column(name = "dong_cd2")
    private Long dongCd2;

    @Column(name = "dong_nm2")
    private String dongNm2;

    @Column(name = "base_year")
    private String baseYear;

    @Builder
    public Regions(String sidoNm, String sigunguNm, String dongNm) {
        this.sidoNm = sidoNm;
        this.sigunguNm = sigunguNm;
        this.dongNm = dongNm;
    }
}
