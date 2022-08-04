package com.example.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication
public class SecutiryFilterChain {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http
        //         .authorizeRequests()
        //         // .antMatchers("/").hasAnyRole("USER") // 인증 영역 설정
        //         .anyRequest().permitAll() // 익명 영역
        //         .and()
        //         .formLogin()
        //         .defaultSuccessUrl("/user", true)
        //         .permitAll()
        //         .and();
        // return http.build();
        http
                // .httpBasic()
                // .and()
                .authorizeRequests()
                .antMatchers("/**", "/static/css/**", "/static/img/**",
                        "/static/js/**", "/static/**", "/**/*", "/room")
                .permitAll()
                // .antMatchers("/api/login").authenticated()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }
    
    
    // public void configure(AuthenticationManagerBuilder auth) throws Exception {
    // auth.inMemoryAuthentication()
    // .withUser(User.builder()
    // .username("user1")
    // .password(passwordEncoder().encode("1234"))
    // .roles("USER"));

    // }
}
