package com.example.pbl.service;

import com.example.pbl.domain.*;
import com.example.pbl.dto.*;
import com.example.pbl.global.exception.DuplicateMemberException;
import com.example.pbl.global.exception.MemberNotFoundException;
import com.example.pbl.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member createLion(LionCreateRequest req) {
        repository.findByName(req.name()).ifPresent(m -> {
            throw new DuplicateMemberException(req.name());
        });
        return repository.save(new Member(req.name(), Major.from(req.major()), req.generation(), Part.from(req.part()), RoleType.LION, req.studentId(), null));
    }

    public Member createStaff(StaffCreateRequest req) {
        repository.findByName(req.name()).ifPresent(m -> {
            throw new DuplicateMemberException(req.name());
        });
        return repository.save(new Member(req.name(), Major.from(req.major()), req.generation(), Part.from(req.part()), RoleType.STAFF, null, Position.from(req.position())));
    }

    public Member findMemberById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public List<Member> findAllMembers() {
        return repository.findAll();
    }

    public List<Member> findByPart(String partName) {
        Part part = Part.from(partName);
        return repository.findByPart(part);
    }

    public Member updateLion(Long id, LionUpdateRequest req) {
        Member m = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        m.updateInfo(Major.from(req.major()), req.generation(), Part.from(req.part()));
        m.updateStudentId(req.studentId());
        return repository.save(m);
    }

    public Member updateStaff(Long id, StaffUpdateRequest req) {
        Member m = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        m.updateInfo(Major.from(req.major()), req.generation(), Part.from(req.part()));
        m.updatePosition(Position.from(req.position()));
        return repository.save(m);
    }

    public void deleteMember(Long id) {
        if (!repository.existsById(id)) throw new MemberNotFoundException(id);
        repository.deleteById(id);
    }
}