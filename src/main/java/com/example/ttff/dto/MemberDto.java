package com.example.ttff.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {
    private String memberId;
    private String password;
}