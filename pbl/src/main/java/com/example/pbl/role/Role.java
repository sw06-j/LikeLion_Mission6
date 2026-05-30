package com.example.pbl.role;
import com.example.pbl.policy.SubmissionPolicy;

public abstract class Role {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String studentId;
    private SubmissionPolicy submissionPolicy;

    public Role(String name, String major, int generation, String part, String studentId, SubmissionPolicy submissionPolicy) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
        this.submissionPolicy = submissionPolicy;
    }

    public String getName() { return name; }

    public void printInfo(String roleName) {
        System.out.println("👤 역할: " + roleName);
        System.out.printf("📌 이름: %s | 🎓 전공: %s | 🔢 기수: %d | 💻 파트: %s\n", name, major, generation, part);
        System.out.println("🆔 학번: " + studentId);
        System.out.println("📝 과제 제출 가능: " + submissionPolicy.getPolicyDescription());
    }

    public abstract void printRoleInfo();
}
