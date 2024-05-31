package com.example.DWShopProject.service;

import com.example.DWShopProject.dto.LoginDto;
import com.example.DWShopProject.dto.MemberDto;
import com.example.DWShopProject.entity.Member;
import com.example.DWShopProject.jwt.JwtUtil;
import com.example.DWShopProject.repository.MemberRepository;
import com.example.DWShopProject.security.MemberRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CartService cartService; // CartService 주입

    public MemberDto signUp(MemberDto memberDto) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        Member member = Member.builder()
                .memberType(MemberRoleEnum.valueOf(memberDto.getMemberType()))
                .memberId(memberDto.getMemberId())
                .memberName(memberDto.getMemberName())
                .password(encodedPassword)
                .birthdate(memberDto.getBirthdate())
                .gender(memberDto.getGender())
                .email(memberDto.getEmail())
                .contact(memberDto.getContact())
                .address(memberDto.getAddress())
                .build();
        member = memberRepository.save(member);

        // 회원 가입 시 장바구니 생성 및 연결
        cartService.createCart(member.getId());

        return mapToDTO(member);
    }

    public MemberDto mapToDTO(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .memberType(member.getMemberType().name())
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .password(null) // 비밀번호 필드를 null로 설정
                .birthdate(member.getBirthdate())
                .gender(member.getGender())
                .email(member.getEmail())
                .contact(member.getContact())
                .address(member.getAddress())
                .build();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDto updateMember(Long id, MemberDto memberDto) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        existingMember.updateMemberInfo(
                memberDto.getMemberName(),
                memberDto.getPassword() != null ? passwordEncoder.encode(memberDto.getPassword()) : null,
                memberDto.getBirthdate(),
                memberDto.getGender(),
                memberDto.getEmail(),
                memberDto.getContact(),
                memberDto.getAddress()
        );
        Member updatedMember = memberRepository.save(existingMember);
        return mapToDTO(updatedMember);
    }

    public MemberDto edit(MemberDto memberDto) {
        Member existingMember = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        existingMember.updateMemberInfo(
                memberDto.getMemberName(),
                memberDto.getPassword() != null ? passwordEncoder.encode(memberDto.getPassword()) : null,
                memberDto.getBirthdate(),
                memberDto.getGender(),
                memberDto.getEmail(),
                memberDto.getContact(),
                memberDto.getAddress()
        );
        Member updatedMember = memberRepository.save(existingMember);
        return mapToDTO(updatedMember);
    }

    public String login(LoginDto loginDto, HttpServletResponse response) {
        try {
            System.out.println("Login attempt for user: " + loginDto.getMemberId()); // 디버깅 로그 추가
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getMemberId(), loginDto.getPassword())
            );

            String token = jwtUtil.createToken(loginDto.getMemberId(), MemberRoleEnum.valueOf(authentication.getAuthorities().iterator().next().getAuthority()));
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600); // 1 hour
            cookie.setPath("/");
            response.addCookie(cookie);

            return token;
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed for user: " + loginDto.getMemberId() + " with message: " + e.getMessage()); // 디버깅 로그 추가
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Invalid username or password: " + e.getMessage();
        } catch (Exception e) {
            System.out.println("Unexpected error for user: " + loginDto.getMemberId() + " with message: " + e.getMessage()); // 디버깅 로그 추가
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Unexpected error: " + e.getMessage();
        }
    }

}
