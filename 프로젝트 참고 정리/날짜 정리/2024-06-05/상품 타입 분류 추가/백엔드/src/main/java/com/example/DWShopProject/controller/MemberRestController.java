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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MemberRestController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public MemberRestController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody MemberDto memberDto) {
        try {
            MemberDto member = memberService.signUp(memberDto);
            return ResponseEntity.ok(member);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    // 모든 사용자 조회 API
    @GetMapping("/members")
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtos = members.stream()
                .map(memberService::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(memberDtos);
    }

    // 관리자 전용 회원 추가 API
    @PostMapping("/members")
    public ResponseEntity<MemberDto> addMember(@RequestBody MemberDto memberDto) {
        MemberDto member = memberService.signUp(memberDto);
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
    public ResponseEntity<MemberDto> updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) {
        MemberDto member = memberService.updateMember(id, memberDto);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 마이페이지 조회 API
    @GetMapping("/mypage")
    public ResponseEntity<?> getMyPage(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        if (memberDetails == null) {
            log.info("User is not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }
        Member member = memberDetails.getMember();
        return ResponseEntity.ok(memberService.mapToDTO(member));
    }

    // 마이페이지 회원 탈퇴 API 및 쿠키 삭제
    @DeleteMapping("/mypage")
    public ResponseEntity<String> deleteMyMember(@AuthenticationPrincipal MemberDetailsImpl memberDetails,
                                                 @CookieValue(value = "token", defaultValue = "", required = false) Cookie jwtCookie,
                                                 HttpServletResponse response) {
        memberService.deleteMember(memberDetails.getMember().getId());
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
        return ResponseEntity.ok("사용자 계정이 성공적으로 삭제되었습니다.");
    }

    // 마이페이지 회원 수정 API
    @PutMapping("/mypage")
    public ResponseEntity<MemberDto> updateMyMember(@RequestBody MemberDto memberDto,
                                                    @AuthenticationPrincipal MemberDetailsImpl memberDetails,
                                                    @CookieValue(value = "token", defaultValue = "", required = false) Cookie jwtCookie,
                                                    HttpServletResponse response) {
        Member currentMember = memberDetails.getMember();
        if (!currentMember.getId().equals(memberDto.getId())) {
            return ResponseEntity.status(403).build();
        }

        MemberDto member = memberService.edit(memberDto);
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return ResponseEntity.ok(member);
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        try {
            log.info("Login attempt for user: {}", loginDto.getMemberId());
            String token = memberService.login(loginDto, response);
            token = token.trim();
            log.info("Generated token: {}", token);

            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            log.error("Error during login for user: {}", loginDto.getMemberId(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // 로그아웃 API
    @PostMapping("/logout")
    private ResponseEntity<String> logout(@CookieValue(value = "token", defaultValue = "", required = false) Cookie jwtCookie,
                                         HttpServletResponse response) {
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return ResponseEntity.ok("로그아웃이 성공적으로 완료되었습니다.");
    }
}
