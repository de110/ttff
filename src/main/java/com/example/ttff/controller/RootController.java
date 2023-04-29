package com.example.ttff.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RootController implements ErrorController {

    @GetMapping({ "/", "/error", "/login" })
    public String webController() {
        return "/";
    }

    public String getErrorPath() {
        return "/error";
    }
}