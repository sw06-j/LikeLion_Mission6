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
    public MemberController(MemberService memberService) { this.memberService = memberService; }

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest req) {
        Member member = memberService.createLion(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(member));
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest req) {
        Member member = memberService.createStaff(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(member));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<MemberResponse> responses = memberService.findAllMembers().stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        Member member = memberService.findMemberById(id);
        if (member == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest req) {
        Member member = memberService.updateLion(id, req);
        if (member == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest req) {
        Member member = memberService.updateStaff(id, req);
        if (member == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}