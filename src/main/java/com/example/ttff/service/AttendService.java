package com.example.ttff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ttff.domain.Attend;
import com.example.ttff.domain.Study;
import com.example.ttff.dto.AttendDto;
import com.example.ttff.repository.AttendRepository;
import com.example.ttff.repository.StudyMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendService {

    private final AttendRepository attendRepository;
    private final StudyMemberRepository studyMemberRepository;

    @Transactional
    public AttendDto.Res registerAttendance(AttendDto.Req attendReq) {
        Study study = studyMemberRepository.findByTitle(attendReq.getStudyName()).get();
        Attend attend = Attend.builder().attendance(attendReq.getAttendance()).study(study)
                .memberId(attendReq.getMemberId()).build();
        return new AttendDto.Res(attendRepository.save(attend));
    }

    @Transactional(readOnly = true)
    public List<AttendDto.Res> getMemberAttend(String memberId) {
        List<Attend> attends = attendRepository.findByMemberId(memberId);
        List<AttendDto.Res> attendDtos = new ArrayList<>();
        attends.forEach(attend -> attendDtos.add(new AttendDto.Res(attend)));
        return attendDtos;
    }

    @Transactional(readOnly = true)
    public List<AttendDto.Res> getAttendList() {
        List<Attend> attends = attendRepository.findAll();
        List<AttendDto.Res> attendDtos = new ArrayList<>();
        attends.forEach(attend->attendDtos.add(new AttendDto.Res(attend)));
        return attendDtos;
    }
}
