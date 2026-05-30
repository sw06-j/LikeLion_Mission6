package com.example.pbl.repository;

import com.example.pbl.role.Role;
import java.util.List;

public interface MemberRepository {
    void save(Role role);
    List<Role> findAll();
    Role findByName(String name);
}