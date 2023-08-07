package com.example.ttff.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginMemberDto {
    private String memberId;
    private String password;

    @Builder
    public LoginMemberDto(String memberId, String password, String roles) {
        this.memberId = memberId;
        this.password = password;
    }
}