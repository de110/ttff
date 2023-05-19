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
import com.example.ttff.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegionController {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;

    @GetMapping("/regions")
    public List<Region> getDongList() {
        return regionRepository.findAll();
    }

    @GetMapping("/regions/si") // 중복 처리
    public List<String> getSi() {
        return regionRepository.selectSidoName();
    }

    @GetMapping("/regions/gun")
    public List<String> getGun(@RequestParam String sido) {
        return regionRepository.selectSigunguName(sido); // 중복 처리
    }

    @GetMapping("/regions/dong")
    public List<Region> getDong(@RequestParam String sido, @RequestParam String gun) {
        return regionRepository.findBySidoNmAndSigunguNm(sido, gun);
    }

    @GetMapping("/region")
    public Region gList(@RequestParam String id) {
        Member member = memberRepository.findByMemberId(id).get();
        return member.getRegion();
    }

    @PatchMapping("/user/{userId}")
    public Member updateRegion(@PathVariable String userId, @RequestBody Member member) {
        Region r = regionRepository.findBySidoNmAndDongNm(member.getRegion().getSidoNm(),
                member.getRegion().getDongNm()).get();

        Member member2 = memberRepository.findByMemberId(userId).get();
        member2.setRegion(r);
        memberRepository.save(member2);
        return member2;
    }
}
