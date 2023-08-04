package com.example.ttff.dto;

import java.util.Collections;
import java.util.UUID;

import com.example.ttff.domain.Member;
import com.example.ttff.domain.Region;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    public static class SignupReq {
        private String memberId;
        private String password;
        private String email;
        private String name;
        private Region region;

        @Builder
        public SignupReq(String memberId, String password, String email, String name, Region region) {
            this.memberId = memberId;
            this.password = password;
            this.email = email;
            this.name = name;
            this.region = region;
        }

        public Member toEntity() {
            return Member.builder()
                    .memberId(this.memberId)
                    .password(this.password)
                    .email(this.email)
                    .name(this.name)
                    .region(this.region)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .uid(UUID.randomUUID())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class MemberReq {
        private Region region;

        @Builder
        public MemberReq(final Region region) {
            this.region = region;
        }
    }

    @Getter
    public static class Res {
        private String memberId;
        private String password;
        private String email;
        private String name;
        private Region region;

        public Res(Member member) {
            this.memberId = member.getMemberId();
            this.password = member.getPassword();
            this.email = member.getEmail();
            this.name = member.getName();
            this.region = member.getRegion();
        }
    }

}
