package com.example.DWTransferScoutProject.auth.controller;

import com.example.DWTransferScoutProject.auth.security.JwtUtil;
import com.example.DWTransferScoutProject.common.account.dto.LoginDto;
import com.example.DWTransferScoutProject.auth.service.AuthService;
import com.example.DWTransferScoutProject.user.dto.UserDto;
import com.example.DWTransferScoutProject.user.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDao, HttpServletResponse response) {
        try {
            String token = authService.login(loginDao, response); // 로그인 서비스에서 토큰 생성 및 반환
            return ResponseEntity.ok(token); // 클라이언트에게 토큰을 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("로그인 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    // 로그아웃 API
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                                         HttpServletResponse response) {
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return ResponseEntity.ok("로그아웃이 성공적으로 완료되었습니다.");
    }

    // ID 중복 확인 API
    @PostMapping("/idcheck")
    public ResponseEntity<?> idCheck(@RequestBody UserDto userDto) {
        Optional<User> optionalUser = authService.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }

    // 토큰으로 사용자 유형 확인 API
    @GetMapping("/user/type")
    public ResponseEntity<?> getUserType(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        String userType = jwtUtil.getAccountTypeFromToken(token);
        return ResponseEntity.ok(Collections.singletonMap("userType", userType)); // userType으로 반환
    }
}
