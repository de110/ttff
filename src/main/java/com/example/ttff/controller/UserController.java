package com.example.ttff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.Member;
import com.example.ttff.dto.LoginMemberDto;
import com.example.ttff.dto.TokenInfo;
import com.example.ttff.repository.MemberRepository;
import com.example.ttff.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final MemberService userService;
    private final MemberRepository userRepository;

    // @PostMapping("/login")
    // public TokenInfo login(@RequestBody MemberLoginRequestDto
    // memberLoginRequestDto) {
    // String memberId = memberLoginRequestDto.getMemberId();
    // String password = memberLoginRequestDto.getPassword();
    // TokenInfo tokenInfo = userService.login(memberId, password);
    // return tokenInfo;
    // }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginMemberDto loginUserDto) {
        String memberId = loginUserDto.getMemberId();
        String password = loginUserDto.getPassword();
        return userService.login(memberId, password);
    }

    @PostMapping("/signup")
    public Member signup(@RequestBody Member user) {
        return userService.signup(user);
    }

    @GetMapping("/signup")
    public Boolean checkUserId(@RequestParam String userid) {
        return userRepository.existsByMemberId(userid);
    }
}