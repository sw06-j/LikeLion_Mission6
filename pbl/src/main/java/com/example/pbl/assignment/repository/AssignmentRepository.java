package com.example.pbl.assignment.repository;

import com.example.pbl.assignment.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByMemberId(Long memberId);
    List<Assignment> findByTitleContaining(String keyword);
}
