package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.dto.DinosaurDto;
import com.example.springsecurityjwt.dto.SignUpRequestDto;
import com.example.springsecurityjwt.entity.Dinosaur;
import com.example.springsecurityjwt.entity.Member;
import com.example.springsecurityjwt.repository.MemberRepository;
import com.example.springsecurityjwt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeesController {



    // 직원 관리 html을 다루는 컨트롤러

    private final MemberService memberService;
    private final MemberRepository memberRepository;

@Autowired
    public EmployeesController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/employees")
    public String getAllDinosaurs(Model model) {
        Iterable<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "employees";
    }

    /* 직원 추가 뷰 */
    @GetMapping("/addemployee")
    public String addemployeeView() {
        return "addemployee";
    }

    /* 직원 추가 API */
    @PostMapping("/api/employees")
    public String addEmployee(SignUpRequestDto signUpRequestDto, RedirectAttributes redirectAttributes) {
        try {
            memberService.signUp(signUpRequestDto);
            return "redirect:/employees"; // 직원 추가 성공 시 직원 목록 페이지로 리다이렉트
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "직원 추가 중 오류가 발생했습니다. 다시 시도해주세요.");
            return "addemployee"; // 직원 추가 실패 시 직원 추가 페이지로 리다이렉트
        }
    }

    @PostMapping("/employees/delete")
    public String deleteEmployee(@RequestParam Long id) {
        // 직원 삭제 로직을 구현합니다.
       memberService.deleteById(id);

        // 삭제 후 리다이렉트할 페이지를 지정합니다.
        return "redirect:/employees";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, Model model) {
        // 직원 정보를 데이터베이스에서 가져옴
        Member member = memberService.findById(id);

        // 가져온 직원 정보를 모델에 추가하여 Thymeleaf로 전달
        model.addAttribute("member", member);

        return "editemployee"; // 수정할 직원 정보를 담은 페이지로 이동
    }

    // 수정된 직원 정보를 저장하는 핸들러
    @PostMapping("/employee/edit")
    public String saveEditedEmployee(@ModelAttribute Member member) {
        // 수정된 직원 정보를 데이터베이스에 저장
        memberService.save(member);

        // 수정 후에는 보통 상세 페이지로 리다이렉트하거나, 수정된 내용을 보여주는 페이지로 이동합니다.
        return "redirect:/employees"; // 수정된 직원 정보를 보여주는 페이지로 리다이렉트
    }





}
