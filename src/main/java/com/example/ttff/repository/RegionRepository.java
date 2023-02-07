package com.example.ttff.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ttff.domain.Regions;

@Repository
public interface RegionRepository extends JpaRepository<Regions, Long> {
    List<Regions> findBySidoNmAndSigunguNm(String SidoNm, String sigunguNm);

    List<Regions> findBySidoNm(String sidoNm);

    Optional<Regions> findBySidoNmAndDongNm(String sidoNm, String dongNm);

    @Query(value = "SELECT distinct re.sidoNm FROM Regions re")
    public List<String> selectSidoName();

    @Query(value = "SELECT DISTINCT re.sigunguNm FROM Regions re WHERE re.sidoNm=?1") // 중복 처리 검색
    public List<String> selectSigunguName(String sidoNm);

}
