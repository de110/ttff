package com.example.ttff.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.Member;
import com.example.ttff.domain.StudyMember;
import com.example.ttff.repository.MemberRepository;
import com.example.ttff.repository.StudyMemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudyMemberController {
    private final StudyMemberRepository smemberRepository;
    private final MemberRepository memberRepository;

    // @PostMapping("/api/member")
    // public Member initMembers(@RequestBody Member member,
    // @AuthenticationPrincipal UserDetails user) {
    // User users = userRepository.findByUsername(user.getUsername()).get();
    // member.setMemb(users);
    // return memberRepository.save(member);
    // }

    @PostMapping("/api/members")
    public StudyMember addMembers(@RequestBody StudyMember smember) {
        smemberRepository.existsByMember(smember.getMember());
        return smemberRepository.save(smember);
    }

    @GetMapping("/exist/member")
    public boolean existsMember(@RequestParam String memberId) {
        Member member = memberRepository.findByMemberId(memberId).get();
        return smemberRepository.existsByMember(member);
    }

    @GetMapping("/member")
    public List<StudyMember> allMem() {
        return smemberRepository.findAll();
    }

}
