package com.example.pbl.policy;

public class LionSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean canSubmit() { return true; }
    @Override
    public String getPolicyDescription() { return "✅ 가능"; }
}
