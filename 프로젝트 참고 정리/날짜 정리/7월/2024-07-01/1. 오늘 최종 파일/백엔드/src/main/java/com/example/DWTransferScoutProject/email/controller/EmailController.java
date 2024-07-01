package com.example.DWTransferScoutProject.email.controller;

import com.example.DWTransferScoutProject.user.repository.UserRepository;
import com.example.DWTransferScoutProject.email.service.EmailService;
import com.example.DWTransferScoutProject.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    Random random = new Random();
    int MAX_EMAIL_ASSOCIATED_ACCOUNTS = 1; // 이메일 하나당 만들 수 있는 계정의 수
    int verificationCode = random.nextInt(888888) + 111111;
}
