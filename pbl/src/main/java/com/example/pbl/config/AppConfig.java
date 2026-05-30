package com.example.pbl.config;

import com.example.pbl.repository.MemberRepository;
import com.example.pbl.repository.MemoryMemberRepository;
import com.example.pbl.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration // 자동 주입(@Service, @Repository) 테스트를 위해 수동 주입 비활성화
public class AppConfig {

    // @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}