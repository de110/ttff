package com.example.login.dto;

import com.example.login.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;

    public User toEntity() {
        User user = User.builder().username(username).password(password).build();
        return user;
    }

}
