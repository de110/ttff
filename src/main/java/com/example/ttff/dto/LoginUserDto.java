package com.example.ttff.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserDto {
    private String memberId;
    private String password;
}