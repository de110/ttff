package com.example.ttff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.Member;
import com.example.ttff.dto.LoginMemberDto;
import com.example.ttff.dto.SignupDto;
import com.example.ttff.dto.TokenInfo;
import com.example.ttff.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    // @PostMapping("/login")
    // public TokenInfo login(@RequestBody MemberLoginRequestDto
    // memberLoginRequestDto) {
    // String memberId = memberLoginRequestDto.getMemberId();
    // String password = memberLoginRequestDto.getPassword();
    // TokenInfo tokenInfo = memberService.login(memberId, password);
    // return tokenInfo;
    // }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginMemberDto loginMemberDto) {
        String memberId = loginMemberDto.getMemberId();
        String password = loginMemberDto.getPassword();
        return memberService.login(memberId, password);
    }

    @PostMapping("/signup")
    public Member signup(@RequestBody SignupDto signupDto) {
        return memberService.signup(signupDto);
    }

    // 유저 조회
    @GetMapping("/user/{member}")
    public Member getUser(@PathVariable String member) {
        return memberService.getUser(member);
    }

}