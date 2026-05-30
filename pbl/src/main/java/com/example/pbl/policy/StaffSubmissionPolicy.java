package com.example.pbl.policy;

public class StaffSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean canSubmit() { return false; }
    @Override
    public String getPolicyDescription() { return "❌ 불가능"; }
}