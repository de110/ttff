package com.example.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.domain.Regions;
import com.example.user.domain.User;
import com.example.user.repository.RegionRepository;
import com.example.user.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegionController {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    @GetMapping("/regions/user")
    public List<Regions> userRegion(@AuthenticationPrincipal UserDetails user) {
        User userregion = userRepository.findByUsername(user.getUsername()).get();
        String gunNm = userregion.getRegion().getSigunguNm();
        return regionRepository.findBySigunguNm(gunNm);
    }

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
    public List<Regions> getDong(@RequestParam String gun) {
        return regionRepository.findBySigunguNm(gun);
    }

    // 동 정보 변경
    @PatchMapping("/user/name")
    public void updateDong(@RequestBody User dong, @AuthenticationPrincipal UserDetails user) {
        User userregion = userRepository.findByUsername(user.getUsername()).get();
        Optional<Regions> re = regionRepository.findByDongNm(dong.getRegion().getDongNm());
        userregion.setRegion(re.get());
        userRepository.save(userregion);
    }

}
