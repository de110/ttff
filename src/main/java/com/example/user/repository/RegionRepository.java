package com.example.user.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.user.domain.Regions;

@Repository
public interface RegionRepository extends JpaRepository<Regions, Long> {
    List<Regions> findBySigunguNm(String sigunguNm);

    List<Regions> findBySidoNm(String sidoNm);

    Optional<Regions> findByDongNm(String dongNm);

    @Query(value = "SELECT distinct re.sidoNm FROM Regions re")
    public List<String> selectSidoName();

}
