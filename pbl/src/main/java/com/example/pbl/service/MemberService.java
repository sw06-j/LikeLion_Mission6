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
    public Role findMemberByName(String name) { return repository.findByName(name); }
    public List<Role> findAllMembers() { return repository.findAll(); }
    public boolean deleteMember(String name) { return repository.deleteByName(name); }
    // (이하 update 등 나머지 생략, 위 구조에 맞춰 작성)
}