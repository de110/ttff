package com.example.ttff.exception;

import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException {
    private long id;

    public MemberNotFoundException(long id) {
        this.id = id;
    }
}
