package com.example.DWShopProject.authentication.controller;

import com.example.DWShopProject.user.common.dto.AccountDTO;
import com.example.DWShopProject.authentication.service.AuthenticationService;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import com.example.DWShopProject.user.dto.UserDTO;
import com.example.DWShopProject.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, JwtUtil jwtUtil, UserService userService) {
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody AccountDTO accountDto) {
        try {
            if (accountDto instanceof UserDTO) {
                authenticationService.signUp((UserDTO) accountDto, userService);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("알 수 없는 계정 유형입니다.");
            }
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("회원가입 중 오류가 발생했습니다.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 중 오류가 발생했습니다.");
        }
    }
}
