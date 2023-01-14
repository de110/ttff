package com.example.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.domain.Member;
import com.example.user.domain.User;
import com.example.user.repository.BoardRepository;
import com.example.user.repository.MemberRepository;
import com.example.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    @PostMapping("/api/member")
    public Member initMembers(@RequestBody Member member, @AuthenticationPrincipal UserDetails user) {
        User users = userRepository.findByUsername(user.getUsername()).get();
        member.setMemb(users);
        return memberRepository.save(member);
    }

    @PostMapping("/api/members")
    public Member addMembers(@RequestBody Member member) {
        return memberRepository.save(member);
    }
}
