package com.example.pbl.service;

import com.example.pbl.domain.role.*;
import com.example.pbl.dto.*;
import com.example.pbl.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;
    public MemberService(MemberRepository repository) { this.repository = repository; }

    public void createLion(LionCreateRequest req) { repository.save(new Lion(req.name(), req.major(), req.generation(), req.part(), req.studentId())); }
    public void createStaff(StaffCreateRequest req) { repository.save(new Staff(req.name(), req.major(), req.generation(), req.part(), req.position())); }

    public Role findMemberByName(String name) { return repository.findByName(name); }
    public List<Role> findAllMembers() { return repository.findAll(); }

    public Lion updateLion(String name, LionUpdateRequest req) {
        Role m = repository.findByName(name);
        if (!(m instanceof Lion)) return null;
        Lion updated = new Lion(name, req.major(), req.generation(), req.part(), req.studentId());
        repository.updateByName(name, updated);
        return updated;
    }

    public Staff updateStaff(String name, StaffUpdateRequest req) {
        Role m = repository.findByName(name);
        if (!(m instanceof Staff)) return null;
        Staff updated = new Staff(name, req.major(), req.generation(), req.part(), req.position());
        repository.updateByName(name, updated);
        return updated;
    }

    public boolean deleteMember(String name) { return repository.deleteByName(name); }
}