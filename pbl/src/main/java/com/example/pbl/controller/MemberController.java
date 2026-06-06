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

    @PostMapping("/staffs")
    public ResponseEntity<String> createStaff(@RequestBody StaffCreateRequest req) {
        memberService.createStaff(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("Staff created");
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(@PathVariable String name) {
        Role member = memberService.findMemberByName(name);
        if (member == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return (member instanceof Lion) ? ResponseEntity.ok(LionResponse.from((Lion) member)) : ResponseEntity.ok(StaffResponse.from((Staff) member));
    }

    @PutMapping("/lions/{name}")
    public ResponseEntity<String> updateLion(@PathVariable String name, @RequestBody LionUpdateRequest req) {
        if (memberService.updateLion(name, req) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Lion updated");
    }

    @PutMapping("/staffs/{name}")
    public ResponseEntity<String> updateStaff(@PathVariable String name, @RequestBody StaffUpdateRequest req) {
        if (memberService.updateStaff(name, req) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Staff updated");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(@PathVariable String name) {
        return memberService.deleteMember(name) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}