package com.example.DWTransferScoutProject.useremail.controller;

import com.example.DWTransferScoutProject.useremail.service.UserEmailService;
import com.example.DWTransferScoutProject.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserEmailController {
    private final UserService userService;
    private final UserEmailService emailService;

    // 생성자 주입
    @Autowired
    public UserEmailController(UserService userService, UserEmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }
}
