package com.example.user.service;

import java.util.List;

// import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.user.domain.User;
import com.example.user.dto.UserDTO;
import com.example.user.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        // user.encryptPassword(passwordEncoder);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() -> {
            return new UsernameNotFoundException("Not Found");
        });
        return new UserDTO(user);
    }


    public List<User> allUsers() {
        return userRepository.findAll();
    }

}
