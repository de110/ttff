package com.example.ttff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.Member;
import com.example.ttff.dto.MemberDto;
import com.example.ttff.dto.TokenInfo;
import com.example.ttff.repository.StudyMemberRepository;
import com.example.ttff.repository.MemberRepository;
import com.example.ttff.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final MemberService userService;
    private final MemberRepository memberRepository;

    // @PostMapping("/login")
    // public TokenInfo login(@RequestBody MemberLoginRequestDto
    // memberLoginRequestDto) {
    // String memberId = memberLoginRequestDto.getMemberId();
    // String password = memberLoginRequestDto.getPassword();
    // TokenInfo tokenInfo = userService.login(memberId, password);
    // return tokenInfo;
    // }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberDto userDto) {
        String memberId = userDto.getMemberId();
        String password = userDto.getPassword();
        return userService.login(memberId, password);
    }

    @GetMapping("/user")
    public Member login(@RequestParam String userId) {
        // String memberId = userDto.getMemberId();
        // String password = userDto.getPassword();
        return memberRepository.findByMemberId(userId).get();
    }

    @PostMapping("/user")
    public Member signup(@RequestBody Member user) {
        return userService.signup(user);
    }

    @GetMapping("/signup")
    public Boolean checkUserId(@RequestParam String userid) {
        return memberRepository.existsByMemberId(userid);
    }

    // @GetMapping(path = "/rooms", params = "username")
    // public List<Member> getRoomList(@RequestParam String username) {
    // // Member user = userRepository.findByMemberId(username).get();
    // return memberRepository.findByMemberId(username);
    // // return chatuserRepository.findAll();
    // }

}