package com.example.pbl.assignment.service;

import com.example.pbl.assignment.domain.Assignment;
import com.example.pbl.assignment.dto.AssignmentCreateRequest;
import com.example.pbl.assignment.dto.AssignmentUpdateRequest;
import com.example.pbl.assignment.repository.AssignmentRepository;
import com.example.pbl.domain.Member;
import com.example.pbl.global.exception.AssignmentNotFoundException;
import com.example.pbl.global.exception.MemberNotFoundException;
import com.example.pbl.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    public Assignment create(Long memberId, AssignmentCreateRequest req) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return assignmentRepository.save(new Assignment(req.title(), req.description(), member));
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> findByMemberId(Long memberId) {
        if (!memberRepository.existsById(memberId)) throw new MemberNotFoundException(memberId);
        return assignmentRepository.findByMemberId(memberId);
    }

    public Assignment findById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(id));
    }

    public List<Assignment> searchByTitle(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword);
    }

    public Assignment update(Long id, AssignmentUpdateRequest req) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(id));
        assignment.update(req.title(), req.description());
        return assignmentRepository.save(assignment);
    }

    public void delete(Long id) {
        if (!assignmentRepository.existsById(id)) throw new AssignmentNotFoundException(id);
        assignmentRepository.deleteById(id);
    }
}
