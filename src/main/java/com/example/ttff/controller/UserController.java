package com.example.ttff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.User;
import com.example.ttff.dto.LoginUserDto;
import com.example.ttff.dto.TokenInfo;
import com.example.ttff.repository.StudyMemberRepository;
import com.example.ttff.repository.UserRepository;
import com.example.ttff.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService memberService;
    private final UserRepository memberRepository;

    // @PostMapping("/login")
    // public TokenInfo login(@RequestBody MemberLoginRequestDto
    // memberLoginRequestDto) {
    // String memberId = memberLoginRequestDto.getMemberId();
    // String password = memberLoginRequestDto.getPassword();
    // TokenInfo tokenInfo = memberService.login(memberId, password);
    // return tokenInfo;
    // }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginUserDto loginUserDto) {
        String memberId = loginUserDto.getMemberId();
        String password = loginUserDto.getPassword();
        return memberService.login(memberId, password);
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return memberService.signup(user);
    }

    @GetMapping("/signup")
    public Boolean checkUserId(@RequestParam String userid) {
        return memberRepository.existsByMemberId(userid);
    }
}