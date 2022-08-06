package com.example.login.service;

import javax.transaction.Transactional;

import com.example.login.domain.User;
import com.example.login.dto.UserDTO;
import com.example.login.repository.UserRepsitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepsitory userRepsitory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User encryptPassword(UserDTO userDTO) {
        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // User user =
        // User.builder().username(userDTO.getUsername()).password(encoder.encode(userDTO.getPassword()))
        // .build();
        return userRepsitory.save(userDTO.toEntity());
    }
}
