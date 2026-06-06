package com.example.pbl.domain.role;

public class Lion extends Role {
    private String studentId;
    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }
    public String getStudentId() { return studentId; }
}