package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.dto.LoginRequestDto;
import com.example.springsecurityjwt.dto.SignUpRequestDto;
import com.example.springsecurityjwt.entity.Member;
import com.example.springsecurityjwt.security.UserDetailsImpl;
import com.example.springsecurityjwt.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {

    //회원가입 로그인 내정보를 다루는 html 컨트롤러
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



    /*로그인 뷰*/
    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        /*이미 로그인된 사용자일 경우 인덱스 페이지로 강제이동.*/
        if (userDetails != null) {
            return "redirect:/";
        }

        return "login";
    }

    /*회원가입 뷰*/
    @GetMapping("/signup")
    public String signUp() {
        return "signUp";
    }

    /*회원가입 API*/
    @PostMapping("/api/signup")
    public String signUp(SignUpRequestDto requestDto, RedirectAttributes redirectAttributes) {
        try {
            memberService.signUp(requestDto);
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
            return "signup"; // 회원가입 실패 시 회원가입 페이지로 리다이렉트
        }
    }



    /*관리자 페이지 뷰*/
    @GetMapping("/myInfo")
    public String system(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember(); // UserDetailsImpl 객체에서 Member 정보 추출
        model.addAttribute("member", member); // 추출된 Member 정보를 모델에 추가
        return "myinfo";
    }



    /*로그인 API*/
    @PostMapping("/api/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse response) {
        memberService.login(requestDto, response);
        return "redirect:/";
    }


    /*403, forbidden -> 인가 실패 시*/
    @GetMapping("/forbidden")
    public String forbidden() {
       
        /*따로 횟수를 기록한다던지 로직*/
        return "redirect:/";
    }

    /*로그아웃 API*/
    @GetMapping("/api/logout")
    public String logout(@CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                         HttpServletResponse response) {
        /*jwt 쿠키를 가지고와서 제거한다.*/
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return "redirect:/";
    }
}
