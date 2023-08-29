package com.example.ttff.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.dto.LoginMemberDto;
import com.example.ttff.dto.MemberDto;
import com.example.ttff.dto.TokenInfo;
import com.example.ttff.dto.MemberDto.SignupReq;
import com.example.ttff.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    // 로그인
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokenInfo login(@RequestBody @Valid LoginMemberDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }

    // 회원가입
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public MemberDto.Res signup(@RequestBody @Valid SignupReq signupReq) {
        return new MemberDto.Res(memberService.signup(signupReq));
    }

    // 사용자 조회
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public MemberDto.Res getMember(@PathVariable final Long id) {
        return new MemberDto.Res(memberService.findById(id));
    }

    // 사용자 삭제
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteMember(@RequestParam final Long id) {
        memberService.deleteById(id);
    }

}