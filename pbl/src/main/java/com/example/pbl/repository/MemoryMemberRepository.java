package com.example.pbl.repository;

import com.example.pbl.domain.role.Role;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private final List<Role> members = new ArrayList<>();

    @Override public void save(Role member) { members.add(member); }
    @Override public Role findByName(String name) { return members.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null); }
    @Override public List<Role> findAll() { return new ArrayList<>(members); }
    @Override public void updateByName(String name, Role member) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(name)) { members.set(i, member); return; }
        }
    }
    @Override public boolean deleteByName(String name) { return members.removeIf(m -> m.getName().equals(name)); }
    @Override public boolean existsByName(String name) { return members.stream().anyMatch(m -> m.getName().equals(name)); }
}