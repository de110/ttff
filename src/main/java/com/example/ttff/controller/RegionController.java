package com.example.ttff.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.Region;
import com.example.ttff.domain.Member;
import com.example.ttff.repository.RegionRepository;
import com.example.ttff.service.RegionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegionController {

    private final RegionRepository regionRepository;
    private final RegionService regionService;

    @GetMapping("/regions")
    public List<Region> getDongList() {
        return regionService.getDongList();
    }

    @GetMapping("/regions/si") // 중복 처리
    public List<String> getSi() {
        return regionService.getSi();
    }

    @GetMapping("/regions/gun")
    public List<String> getGun(@RequestParam String sido) {
        return regionService.getGun(sido); // 중복 처리
    }

    @GetMapping("/regions/dong")
    public List<Region> getDong(@RequestParam String sido, @RequestParam String gun) {
        return regionRepository.findBySidoNmAndSigunguNm(sido, gun);
    }

    @GetMapping("/region")
    public Region getList(@RequestParam String id) {
        return regionService.getMemberRegion(id);
    }

    @PatchMapping("/member/{memberId}")
    public Member updateMemberRegion(@PathVariable String memberId, @RequestBody Member member) {
        return regionService.updateMemberRegion(memberId, member);
    }
}
