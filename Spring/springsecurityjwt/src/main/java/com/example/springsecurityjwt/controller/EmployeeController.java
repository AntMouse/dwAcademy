package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entity.Member;
import com.example.springsecurityjwt.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

	//직원 소개 html 컨트롤러

	private final MemberRepository memberRepository;

	public EmployeeController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}



	@GetMapping("/Employee")
	public String getDino(Model model) {

		Member manager1 = memberRepository.findById(1L).orElse(null);
		Member manager2 = memberRepository.findById(2L).orElse(null);
		Member security1 = memberRepository.findById(3L).orElse(null);
		Member security2 = memberRepository.findById(4L).orElse(null);
		Member doctor1 = memberRepository.findById(5L).orElse(null);
		Member doctor2 = memberRepository.findById(6L).orElse(null);
		Member guide1 = memberRepository.findById(7L).orElse(null);
		Member guide2 = memberRepository.findById(8L).orElse(null);
		Member keeper1 = memberRepository.findById(9L).orElse(null);
		Member keeper2 = memberRepository.findById(10L).orElse(null);




		model.addAttribute("manager1", manager1);
		model.addAttribute("manager2", manager2);
		model.addAttribute("security1", security1);
		model.addAttribute("security2", security2);
		model.addAttribute("doctor1", doctor1);
		model.addAttribute("doctor2", doctor2);
		model.addAttribute("guide1", guide1);
		model.addAttribute("guide2", guide2);
		model.addAttribute("keeper1", keeper1);
		model.addAttribute("keeper2", keeper2);
		return "Employee";
	}
}
