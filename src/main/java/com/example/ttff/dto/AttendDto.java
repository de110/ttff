package com.example.ttff.dto;

import com.example.ttff.domain.Attend;
import com.example.ttff.domain.Study;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttendDto {
    private String memberId;
    private String attendance;
    private Study study;

    @Builder
    public AttendDto(String memberId, String attendance, Study study) {
        this.memberId = memberId;
        this.attendance = attendance;
        this.study = study;
    }

    @Getter
    @NoArgsConstructor
    public static class Req {
        private String memberId;
        private String studyName;
        private String attendance;

        @Builder
        public Req(AttendDto attendDto) {
            this.memberId = attendDto.getMemberId();
            this.studyName = attendDto.getStudy().getTitle();
            this.attendance = attendDto.getAttendance();
        }

    }

    @Getter
    public static class Res {
        private String memberId;
        private String studyName;
        private String attendance;

        public Res(Attend attend) {
            this.memberId = attend.getMemberId();
            this.studyName = attend.getStudy().getTitle();
            this.attendance = attend.getAttendance();
        }
    }

}
