package com.example.ttff.dto;

import com.example.ttff.domain.Member;
import com.example.ttff.domain.Region;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupDto {
    private String memberId;
    private String password;
    private String email;
    private String name;
    private Region region;

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
