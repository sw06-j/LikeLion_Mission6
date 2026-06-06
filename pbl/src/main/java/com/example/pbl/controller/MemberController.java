package com.example.pbl.controller;

import com.example.pbl.domain.role.Lion;
import com.example.pbl.domain.role.Staff;
import com.example.pbl.dto.*;
import com.example.pbl.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public String createLion(@RequestBody LionCreateRequest req) {
        memberService.createLion(req);
        return "Lion created";
    }

    @PostMapping("/staffs")
    public String createStaff(@RequestBody StaffCreateRequest req) {
        memberService.createStaff(req);
        return "Staff created";
    }

    @GetMapping("/{name}")
    public Object getMember(@PathVariable String name) {
        return memberService.findMemberByName(name);
    }

    @PutMapping("/lions/{name}")
    public String updateLion(@PathVariable String name, @RequestBody LionUpdateRequest req) {
        memberService.updateLion(name, req);
        return "Lion updated";
    }

    @PutMapping("/staffs/{name}")
    public String updateStaff(@PathVariable String name, @RequestBody StaffUpdateRequest req) {
        memberService.updateStaff(name, req);
        return "Staff updated";
    }

    @DeleteMapping("/{name}")
    public String deleteMember(@PathVariable String name) {
        memberService.deleteMember(name);
        return "Member deleted";
    }
}