package com.example.user.controller;

import java.security.Principal;
import java.util.List;

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

import com.example.user.domain.User;
import com.example.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
// @Controller
@RequiredArgsConstructor
// @SessionAttributes("")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    // @GetMapping("/home")
    // @ResponseBody
    // public String home() {
    //     return "Home";
    // }

    // @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    // @GetMapping("/user")
    // public Authentication getUserSecurityInfo(User user) {
    // return SecurityContextHolder.getContext().getAuthentication();
    // }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    // @GetMapping("/admin")
    // public Authentication getAdminSecurityInfo() {
    // return SecurityContextHolder.getContext().getAuthentication();
    // }

    // @GetMapping("/signup")
    // public String user() {
    //     User user = User.builder().userName("user1").password(passwordEncoder.encode("1234")).role("USER").build();
    //     userService.saveUser(user);
    //     return "/user";
    // }

    // session test
    @PostMapping("/user")
    // @ResponseBody
    public User signup(@RequestBody User user) {
        // user.getPassword()
        user = User.builder().userName(user.getUserName()).password(passwordEncoder.encode(user.getPassword()))
                .role("USER").build();
        return userService.saveUser(user);
    }

    // @GetMapping("/user")
    // public UserDetails login(@RequestParam String userName) throws UsernameNotFoundException {
    //     return userService.loadUserByUsername(userName);
    // }
    // // @CrossOrigin
    // @PostMapping("/signin")
    // public UserDetails login(@RequestBody User user) throws UsernameNotFoundException {
    //     return userService.loadUserByUsername(user.getUserName());
    // }

    @GetMapping("/loging")
    public String currentUser(@AuthenticationPrincipal User user) {
        // ChatMember chatMember = new ChatMember(); // room member 설정
        // chatMember = chatMemberRepository.findById(1L).get();
        // chatMember.setUserId(user);
        return user.getUserName();
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> users() {
        return userService.allUsers();
    }

}
