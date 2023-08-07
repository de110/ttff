package com.example.ttff.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.Member;
import com.example.ttff.dto.LoginMemberDto;
import com.example.ttff.dto.MemberDto;
import com.example.ttff.dto.TokenInfo;
import com.example.ttff.dto.MemberDto.SignupReq;
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
    public TokenInfo login(@RequestBody LoginMemberDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }

    // 회원가입
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MemberDto.Res signup(@RequestBody SignupReq signupReq) {
        return new MemberDto.Res(memberService.signup(signupReq));
    }

    // 사용자 조회
    @GetMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberDto.Res getUser(@PathVariable final Long id) {
        return new MemberDto.Res(memberService.findById(id));
    }

    @GetMapping("/member")
    public String getMember() {
        return memberService.getMember();
    }

}