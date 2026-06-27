package com.example.pbl.service;

import com.example.pbl.domain.Member;
import com.example.pbl.domain.RoleType;
import com.example.pbl.dto.*;
import com.example.pbl.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;
    public MemberService(MemberRepository repository) { this.repository = repository; }

    public Member createLion(LionCreateRequest req) {
        return repository.save(new Member(req.name(), req.major(), req.generation(), req.part(), RoleType.LION, req.studentId(), null));
    }

    public Member createStaff(StaffCreateRequest req) {
        return repository.save(new Member(req.name(), req.major(), req.generation(), req.part(), RoleType.STAFF, null, req.position()));
    }

    public Member findMemberById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Member> findAllMembers() {
        return repository.findAll();
    }

    public Member updateLion(Long id, LionUpdateRequest req) {
        Member m = repository.findById(id).orElse(null);
        if (m == null || m.getRoleType() != RoleType.LION) return null;
        
        m.updateInfo(req.major(), req.generation(), req.part());
        m.updateStudentId(req.studentId());
        return repository.save(m);
    }

    public Member updateStaff(Long id, StaffUpdateRequest req) {
        Member m = repository.findById(id).orElse(null);
        if (m == null || m.getRoleType() != RoleType.STAFF) return null;
        
        m.updateInfo(req.major(), req.generation(), req.part());
        m.updatePosition(req.position());
        return repository.save(m);
    }

    public boolean deleteMember(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}