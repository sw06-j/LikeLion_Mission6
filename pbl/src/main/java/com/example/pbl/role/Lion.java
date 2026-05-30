package com.example.pbl.role;
import com.example.pbl.policy.LionSubmissionPolicy;

public class Lion extends Role {
    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part, studentId, new LionSubmissionPolicy());
    }
    @Override
    public void printRoleInfo() {
        super.printInfo("아기사자");
    }
}