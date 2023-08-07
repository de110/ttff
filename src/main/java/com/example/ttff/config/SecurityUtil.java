package com.example.ttff.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getCurrentMemberId() {
        // final Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // log.info("authen:" + authentication.toString());
        // if (authentication == null || authentication.getName() == "anonymousUser") {
        // throw new RuntimeException("로그인 유저 정보가 없습니다.");
        // }
        // return authentication.getName();
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        return user.getName();
    }
}
