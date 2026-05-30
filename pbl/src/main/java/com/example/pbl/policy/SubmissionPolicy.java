package com.example.pbl.policy;

public interface SubmissionPolicy {
    boolean canSubmit();
    String getPolicyDescription();
}