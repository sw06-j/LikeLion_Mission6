package com.example.pbl.repository;

import com.example.pbl.role.Role;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository // 스프링 컨테이너에 Bean으로 자동 등록
public class MemoryMemberRepository implements MemberRepository {
    private final List<Role> store = new ArrayList<>();

    @Override
    public void save(Role role) { store.add(role); }

    @Override
    public List<Role> findAll() { return store; }

    @Override
    public Role findByName(String name) {
        return store.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }
}