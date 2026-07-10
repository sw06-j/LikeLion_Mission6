package com.example.pbl.controller;

import com.example.pbl.domain.Member;
import com.example.pbl.dto.*;
import com.example.pbl.service.MemberService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MemberResponse.from(memberService.createLion(req)));
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MemberResponse.from(memberService.createStaff(req)));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(
            @RequestParam(required = false) String part) {
        List<Member> members = (part != null && !part.isBlank())
                ? memberService.findByPart(part)
                : memberService.findAllMembers();
        List<MemberResponse> responses = members.stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(MemberResponse.from(memberService.findMemberById(id)));
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest req) {
        return ResponseEntity.ok(MemberResponse.from(memberService.updateLion(id, req)));
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest req) {
        return ResponseEntity.ok(MemberResponse.from(memberService.updateStaff(id, req)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}