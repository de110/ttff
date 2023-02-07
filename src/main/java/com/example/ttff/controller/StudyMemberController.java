package com.example.ttff.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.StudyMember;
import com.example.ttff.domain.Member;
import com.example.ttff.repository.BoardRepository;
import com.example.ttff.repository.StudyMemberRepository;
import com.example.ttff.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudyMemberController {
    private final StudyMemberRepository memberRepository;
    private final MemberRepository userRepository;

    // @PostMapping("/api/member")
    // public Member initMembers(@RequestBody Member member,
    // @AuthenticationPrincipal UserDetails user) {
    // User users = userRepository.findByUsername(user.getUsername()).get();
    // member.setMemb(users);
    // return memberRepository.save(member);
    // }

    @PostMapping("/api/members")
    public StudyMember addMembers(@RequestBody StudyMember smember) {
        // memberRepository.findById(smember.getMember().getMemberId());
        return memberRepository.save(smember);
    }

    @GetMapping("/member")
    public List<StudyMember> allMem() {
        return memberRepository.findAll();
        // return memberRepository.findDistinctByTitle("study1");
        // return memberRepository.select();
    }

}
