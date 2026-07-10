package com.example.pbl.global.exception;

public class AssignmentNotFoundException extends RuntimeException {
    public AssignmentNotFoundException(Long id) {
        super("해당 과제를 찾을 수 없습니다. id: " + id);
    }
}
