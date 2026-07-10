package com.example.pbl.repository;

import com.example.pbl.domain.Member;
import com.example.pbl.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByPart(Part part);
    Optional<Member> findByName(String name);
}