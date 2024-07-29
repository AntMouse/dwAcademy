package com.example.DWShopProject.authentication.controller;

import com.example.DWShopProject.authentication.service.AuthenticationService;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import com.example.DWShopProject.user.dto.LoginDTO;
import com.example.DWShopProject.user.dto.UserDTO;
import com.example.DWShopProject.user.dto.UserRegistrationDTO;
import com.example.DWShopProject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationDTO userRegDto) {
        UserDTO registeredUser = authenticationService.registerUser(userRegDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDto) {
        String token = authenticationService.loginUser(loginDto);
        return ResponseEntity.ok(token);
    }
}
