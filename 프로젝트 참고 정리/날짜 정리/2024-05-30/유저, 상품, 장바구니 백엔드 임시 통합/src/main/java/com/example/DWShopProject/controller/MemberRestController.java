package com.example.DWShopProject.controller;
import com.example.DWShopProject.dto.LoginDto;
import com.example.DWShopProject.dto.MemberDto;
import com.example.DWShopProject.entity.Member;
import com.example.DWShopProject.repository.MemberRepository;
import com.example.DWShopProject.security.MemberDetailsImpl;
import com.example.DWShopProject.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
// @CrossOrigin("http://localhost:3000/")
public class MemberRestController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public MemberRestController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    /*회원가입 API*/
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody MemberDto memberDto) {
        try {
            Member member = memberService.signUp(memberDto);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    // 모든 사용자 뷰 API
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = (List<Member>) memberRepository.findAll();
        return ResponseEntity.ok(members);
    }

    /* 관리자 전용 회원 추가 API */
    @PostMapping("/members")
    public ResponseEntity<Member> addMember(@RequestBody MemberDto memberDto) {
        Member member = memberService.signUp(memberDto);
        return ResponseEntity.ok(member);

    }

    // 관리자 전용 회원 삭제 API
    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.ok("사용자가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("사용자 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    // 관리자 전용 회원 수정 API
    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) {
        Member member = memberService.updateMember(id, memberDto);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* 마이페이지 조회 API */
    @GetMapping("/mypage")
    public ResponseEntity<Member> getMyPage(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        Member member = memberDetails.getMember(); // MemberDetailsImpl 객체에서 Member 정보 추출
        return ResponseEntity.ok(member);
    }

    // mypage 회원탈퇴 API 및 header 쿠키삭제
    @DeleteMapping("/mypage")
    public ResponseEntity<String> deleteMyMember(@AuthenticationPrincipal MemberDetailsImpl memberDetails,
                                                 @CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                                                 HttpServletResponse response) {
        memberService.deleteMember(memberDetails.getMember().getId());
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
        return ResponseEntity.ok("사용자 계정이 성공적으로 삭제되었습니다.");
    }

    // mypage 회원 수정 API
    @PutMapping("/mypage")
    public ResponseEntity<Member> updateMyMember(@RequestBody MemberDto memberDto,
                                                 @AuthenticationPrincipal MemberDetailsImpl memberDetails,
                                                 @CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                                                 HttpServletResponse response) {
        Member currentMember = memberDetails.getMember();
        if (!currentMember.getId().equals(memberDto.getId())) {
            return ResponseEntity.status(403).build();
        }

        Member member = memberService.edit(memberDto);
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return ResponseEntity.ok(member);
    }

    /*로그인 API*/
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDao, HttpServletResponse response) {
        try {
            memberService.login(loginDao, response);
            return ResponseEntity.ok("로그인이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("로그인 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    /*로그아웃 API*/
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                                         HttpServletResponse response) {
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return ResponseEntity.ok("로그아웃이 성공적으로 완료되었습니다.");
    }
}
