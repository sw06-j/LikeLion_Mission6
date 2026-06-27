package com.example.pbl.dto;

import com.example.pbl.domain.Member;

public record MemberResponse(
        Long id,
        String name,
        String major,
        int generation,
        String part,
        String roleName,
        String studentId,
        String position
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getMajor() != null ? member.getMajor().getDisplayName() : null,
                member.getGeneration(),
                member.getPart() != null ? member.getPart().getDisplayName() : null,
                member.getRoleType() != null ? member.getRoleType().getDisplayName() : null,
                member.getStudentId(),
                member.getPosition() != null ? member.getPosition().getDisplayName() : null
        );
    }
}
