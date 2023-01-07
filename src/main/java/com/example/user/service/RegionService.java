package com.example.user.service;

import org.springframework.stereotype.Service;

import com.example.user.repository.RegionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

}
