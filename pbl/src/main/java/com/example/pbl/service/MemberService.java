package com.example.pbl.service;

import com.example.pbl.repository.MemberRepository;
import com.example.pbl.role.Role;
import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service // 스프링 컨테이너에 Bean으로 자동 등록
public class MemberService {
    private final MemberRepository repository;

    // @Autowired
    // 생성자가 1개일 경우 @Autowired 어노테이션 생략 가능
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public boolean register(Role role) {
        if (repository.findByName(role.getName()) != null) return false;
        repository.save(role);
        return true;
    }

    public List<Role> getAllMembers() { return repository.findAll(); }

    public Role searchByName(String name) { return repository.findByName(name); }
}