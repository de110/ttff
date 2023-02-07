package com.example.ttff.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.Regions;
import com.example.ttff.domain.Member;
import com.example.ttff.repository.RegionRepository;
import com.example.ttff.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegionController {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;

    // @GetMapping("/regions/user")
    // public List<Regions> userRegion(@AuthenticationPrincipal UserDetails user) {
    // User userregion = userRepository.findByUsername(user.getUsername()).get();
    // String gunNm = userregion.getRegion().getSigunguNm();
    // return regionRepository.findBySigunguNm(gunNm);
    // }

    @GetMapping("/regions")
    public List<Regions> getDongList() {
        return regionRepository.findAll();
    }

    @GetMapping("/regions/si") // 중복 처리
    public List<String> getSi() {
        return regionRepository.selectSidoName();
    }

    @GetMapping("/regions/gun")
    public List<String> getGun(@RequestParam String sido) {
        // return regionRepository.findBySidoNm(sido);
        return regionRepository.selectSigunguName(sido); // 중복 처리
    }

    @GetMapping("/regions/dong")
    public List<Regions> getDong(@RequestParam String sido, @RequestParam String gun) {
        return regionRepository.findBySidoNmAndSigunguNm(sido, gun);
    }

    // @GetMapping("/region")
    // public List<Regions> gList(@RequestParam String id) {
    // Member member = memberRepository.findByMemberId(id).get();
    // // regionRepository.findById(member.getRegion().getSigunguNm());
    // String gun = member.getRegion().getSigunguNm();
    // return regionRepository.findBySigunguNm(gun);
    // }
    @GetMapping("/region")
    public Regions gList(@RequestParam String id) {
        Member member = memberRepository.findByMemberId(id).get();
        // return regionRepository.findBySidoNmAndSigunguNm(re.getSidoNm(),
        // re.getSigunguNm());
        return member.getRegion();
    }

    @PatchMapping("/user/{userId}")
    public Member updateRegion(@PathVariable String userId, @RequestBody Member member) {
        Regions r = regionRepository.findBySidoNmAndDongNm(member.getRegion().getSidoNm(),
                member.getRegion().getDongNm()).get();

        Member member2 = memberRepository.findByMemberId(userId).get();
        member2.setRegion(r);
        memberRepository.save(member2);
        // return member.getRegion().getSidoNm();
        return member2;
    }

    // @PatchMapping("/")
    // // 사용자 지역 정보 변경
    // @PatchMapping("/user/name")
    // public void updateDong(@RequestBody User dong, @AuthenticationPrincipal
    // UserDetails user) {
    // User userregion = userRepository.findByUsername(user.getUsername()).get();
    // Optional<Regions> re =
    // regionRepository.findByDongNm(dong.getRegion().getDongNm());
    // userregion.setRegion(re.get());
    // userRepository.save(userregion);
    // }
}
