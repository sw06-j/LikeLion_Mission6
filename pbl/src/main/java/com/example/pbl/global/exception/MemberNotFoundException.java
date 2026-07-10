package com.example.pbl.global.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("해당 멤버를 찾을 수 없습니다. id: " + id);
    }
}
