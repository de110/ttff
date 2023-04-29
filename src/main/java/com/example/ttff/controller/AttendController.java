package com.example.ttff.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttff.domain.Attend;
import com.example.ttff.repository.AttendRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AttendController {
    private final AttendRepository attendRepository;

    @GetMapping("/attend")
    public List<Attend> getStudyList() {
        // set one
        // Attend attend =
        // Attend.builder().name("user1").attend("출석").studyName("studyName1").build();
        // return attendRepository.save(attend);
        return attendRepository.findAll();
    }

    @GetMapping("/studyAttend")
    public List<Attend> getAttend(@RequestParam String studyName) {
        List<Attend> attend = attendRepository.findByStudyName(studyName);
        // studyName = attend.get().getStudyName();
        return attend;
    }

    @PostMapping("/studyAttend")
    public Attend setAttend(@RequestBody Attend attend) {
        return attendRepository.save(attend);
    }
}
