package com.example.ttff.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.dto.AttendDto;
import com.example.ttff.service.AttendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AttendController {
    private final AttendService attendService;

    // 스터디원 출석 정보 가져오기
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/{study_name}/attendance", method = RequestMethod.GET, params = "memberId")
    public List<AttendDto.Res> getStudyMemberAttend(@PathVariable("study_name") String studyName,
            @RequestParam String memberId) {
        return attendService.getMemberAttend(memberId);
    }

    // 출석 등록
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/attendance", method = RequestMethod.POST)
    public AttendDto.Res registerAttendance(@RequestBody AttendDto.Req req) {
        return attendService.registerAttendance(req);
    }

    // 전체 출석 정보
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/{study_name}/attendance", method = RequestMethod.GET)
    public List<AttendDto.Res> allAttendanceList() {
        return attendService.getAttendList();
    }
}
