package com.example.user.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.user.domain.Board;
import com.example.user.domain.Regions;
import com.example.user.domain.User;
import com.example.user.repository.RegionRepository;
import com.example.user.repository.UserRepository;
import com.example.user.service.BoardService;
import com.example.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;

    @PostMapping("/user")
    public User signup(@RequestBody User user) {
        // log.info("Region? {} ", user.getRegion());
        Optional<Regions> re = regionRepository.findByDongNm(user.getRegion().getDongNm());
        // user.setRegion(re.get());
        user = User.builder().userName(user.getUsername()).password(passwordEncoder.encode(user.getPassword()))
                .role("USER").region(re.get()).build();
        return userService.saveUser(user);
    }

    @GetMapping("/se")
    public User user() {
        User user = userRepository.findById(1L).get();
        // regionRepository.findByDongNm(user.getRegion().get)
        return user;
    }

    @GetMapping("/users")
    public List<User> users() {
        return userService.allUsers();
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        userService.loadUserByUsername(user.getUsername());
        return "login";
    }

    @GetMapping("/loging")
    public UserDetails currentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

}
