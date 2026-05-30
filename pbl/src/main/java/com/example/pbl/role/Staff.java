package com.example.pbl.role;
import com.example.pbl.policy.StaffSubmissionPolicy;

public class Staff extends Role {
    public Staff(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part, studentId, new StaffSubmissionPolicy());
    }
    @Override
    public void printRoleInfo() {
        super.printInfo("운영진");
    }
}