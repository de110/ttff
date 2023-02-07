package com.example.ttff.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class RootController implements ErrorController {

    @GetMapping({ "/", "/error", "/login" })
    public String webController() {
        return "/";
    }

    // @Override
    public String getErrorPath() {
        return "/error";
    }
}