package com.example.pbl.controller;

import com.example.pbl.domain.role.*;
import com.example.pbl.dto.*;
import com.example.pbl.service.MemberService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    public MemberController(MemberService memberService) { this.memberService = memberService; }

    @PostMapping("/lions")
    public ResponseEntity<String> createLion(@RequestBody LionCreateRequest req) {
        memberService.createLion(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lion created");
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(@PathVariable String name) {
        Role member = memberService.findMemberByName(name);
        if (member == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return (member instanceof Lion) ? ResponseEntity.ok(LionResponse.from((Lion) member)) : ResponseEntity.ok(member);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(@PathVariable String name) {
        return memberService.deleteMember(name) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}