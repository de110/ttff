package com.example.ttff.service;

import org.springframework.stereotype.Service;

import com.example.ttff.repository.RegionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

}
