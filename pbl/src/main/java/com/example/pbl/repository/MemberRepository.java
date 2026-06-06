package com.example.pbl.repository;

import com.example.pbl.domain.role.Role;

public interface MemberRepository {
    void save(Role member);
    Role findByName(String name);
    void updateByName(String name, Role member);
    boolean deleteByName(String name);
    boolean existsByName(String name);
}