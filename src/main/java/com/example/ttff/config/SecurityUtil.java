package com.example.ttff.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getCurrentMemberId() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        return user.getName();
    }
}
