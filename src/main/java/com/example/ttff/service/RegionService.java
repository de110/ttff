package com.example.ttff.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ttff.domain.Member;
import com.example.ttff.domain.Region;
import com.example.ttff.repository.MemberRepository;
import com.example.ttff.repository.RegionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;

    public List<Region> getDongList() {
        return regionRepository.findAll();
    }

    public List<String> getSi() {
        return regionRepository.selectSidoName();
    }

    public List<String> getGun(String sido) {
        return regionRepository.selectSigunguName(sido); // 중복 처리
    }

    public List<Region> getDong(String sido, String gun) {
        return regionRepository.findBySidoNmAndSigunguNm(sido, gun);
    }

    public Region getMemberRegion(String id) {
        Member member = memberRepository.findByMemberId(id).get();
        return member.getRegion();
    }

    public Member updateMemberRegion(String memberId, Member member) {
        Region region = regionRepository.findBySidoNmAndDongNm(member.getRegion().getSidoNm(),
                member.getRegion().getDongNm()).get();

        Member updatedMember = memberRepository.findByMemberId(memberId).get();
        updatedMember.updateRegion(region);
        memberRepository.save(updatedMember);
        return updatedMember;
    }
}
