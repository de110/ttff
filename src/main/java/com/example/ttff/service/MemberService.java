package com.example.ttff.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ttff.config.SecurityUtil;
import com.example.ttff.domain.Member;
import com.example.ttff.dto.TokenInfo;
import com.example.ttff.dto.MemberDto.SignupReq;
import com.example.ttff.exception.MemberNotFoundException;
import com.example.ttff.jwt.JwtTokenProvider;
import com.example.ttff.repository.MemberRepository;
import com.example.ttff.repository.RegionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signup(SignupReq signupReq) {
        Member member = Member.builder()
                .memberId(signupReq.getMemberId())
                .password(passwordEncoder.encode(signupReq.getPassword()))
                .email(signupReq.getEmail())
                .name(signupReq.getName())
                .region(signupReq.getRegion())
                .roles(Collections.singletonList("ROLE_USER"))
                .uid(UUID.randomUUID())
                .build();
        return memberRepository.save(member);
    }

    @Transactional
    public TokenInfo login(String memberId, String password) {
        // 권한 설정
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId,
                password, roles);
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername
        // 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info("auth:" + authentication);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        // TokenInfo tokenInfo = TokenInfo.builder().accessToken("password").build();
        return tokenInfo;
    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        member.orElseThrow(() -> new MemberNotFoundException(id));
        return member.get();
    }

    // public static String getCurrentMemberId() {
    // final Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // if (authentication == null || authentication.getName() == "anonymousUser") {
    // throw new RuntimeException("No authentication information.");
    // }
    // return authentication.getName();
    // }

    // @Transactional(readOnly = true)
    // public Member getMember() {
    // return isMemberCurrent();
    // }

    // @Transactional(readOnly = true)
    // public String getMember() {
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // log.info("ee");
    // if (authentication == null || authentication.getPrincipal() ==
    // "anonymousUser") {
    // return authentication.getName();
    // } else {
    // Member member =
    // memberRepository.findByMemberId(authentication.getName()).orElseThrow();
    // return member.getMemberId();
    // }
    // }
    @Transactional(readOnly = true)
    public String getMember() {
        log.info("tt:" + SecurityUtil.getCurrentMemberId());
        memberRepository.findByMemberId(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("there is no user information"));
        return SecurityUtil.getCurrentMemberId();
    }
}