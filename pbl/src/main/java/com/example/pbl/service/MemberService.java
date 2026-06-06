package com.example.pbl.service;

import com.example.pbl.domain.role.*;
import com.example.pbl.dto.*;
import com.example.pbl.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository repository;
    public MemberService(MemberRepository repository) { this.repository = repository; }

    public Lion createLion(LionCreateRequest req) {
        if (repository.existsByName(req.name())) return null;
        Lion lion = new Lion(req.name(), req.major(), req.generation(), req.part(), req.studentId());
        repository.save(lion);
        return lion;
    }

    public Staff createStaff(StaffCreateRequest req) {
        if (repository.existsByName(req.name())) return null;
        Staff staff = new Staff(req.name(), req.major(), req.generation(), req.part(), req.position());
        repository.save(staff);
        return staff;
    }

    public Role findMemberByName(String name) { return repository.findByName(name); }

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