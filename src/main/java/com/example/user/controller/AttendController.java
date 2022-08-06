package com.example.user.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.domain.Attend;
import com.example.user.repository.AttendRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AttendController {
    private final AttendRepository attendRepository;

    @GetMapping("/member")
    public Attend getStudyList() {
        // set one
        Attend attend = Attend.builder().name("user1").attend("출석").studyName("studyName1").build();
        return attendRepository.save(attend);
    }

    @GetMapping("/studyAttend")
    public Attend setAttend(@RequestParam String studyName) {
        Optional<Attend> attend = attendRepository.findById(1L);
        studyName = attend.get().getStudyName();
        return attend.get();
    }
}
