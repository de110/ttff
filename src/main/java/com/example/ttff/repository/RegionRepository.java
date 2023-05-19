package com.example.ttff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ttff.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findBySidoNmAndSigunguNm(String SidoNm, String sigunguNm);

    List<Region> findBySidoNm(String sidoNm);

    Optional<Region> findBySidoNmAndDongNm(String sidoNm, String dongNm);

    @Query(value = "SELECT distinct re.sidoNm FROM Regions re")
    public List<String> selectSidoName();

    @Query(value = "SELECT DISTINCT re.sigunguNm FROM Regions re WHERE re.sidoNm=?1") // 중복 처리 검색
    public List<String> selectSigunguName(String sidoNm);

}
