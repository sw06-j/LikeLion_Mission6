package com.example.pbl;

import com.example.pbl.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PblApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PblApplication.class, args);

		// 컨테이너에서 Bean을 정상적으로 가져오는지 확인
		MemberService memberService = context.getBean(MemberService.class);
		System.out.println("✅ 스프링 컨테이너가 주입한 MemberService: " + memberService);

	}
}
