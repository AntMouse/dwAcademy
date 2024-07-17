package com.example.DWShopProject.email.controller;


import com.example.DWShopProject.account.dto.UserDto;
import com.example.DWShopProject.account.entity.User;
import com.example.DWShopProject.account.repository.UserRepository;
import com.example.DWShopProject.email.service.EmailService;
import com.example.DWShopProject.account.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
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

    // 회원가입시 이메일 인증번호 발송
    @PostMapping("/email")
    public ResponseEntity<?> emailAuth(@RequestBody UserDto userDto) {
        int verificationCode = random.nextInt(888888) + 111111;

        List<User> users = userRepository.findAllByEmail(userDto.getEmail());
        if (!users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("하나의 이메일에 하나의 계정만 만들 수 있습니다.");
        } else {
            emailService.sendVerificationCode(userDto.getEmail(), verificationCode);
        }
        return ResponseEntity.ok(verificationCode);
    }


    // 이메일로 아이디 찾기
    @PostMapping("/findid")
    public ResponseEntity<String> findUserByEmail(@RequestBody UserDto userDto) {
        List<User> users = userService.findUsersByEmail(userDto);

        if (!users.isEmpty()) {
            emailService.sendEmailWithUserIds(userDto.getEmail(), users);
            return ResponseEntity.ok("메일 발송 완료");
        } else {
            // 아이디가 존재하지 않을 경우 Bad Request 반환
            return ResponseEntity.badRequest().body("아이디가 존재하지 않습니다.");
        }
    }

    //비밀번호 찾기
    @PostMapping("/findpassword")
    public ResponseEntity<String> findUserByUserId(@RequestBody UserDto userDto) {
        Optional<User> optionalUser = userService.findUserByUserId(userDto);

        if (optionalUser.isPresent()) {
            int verificationCode = random.nextInt(888888) + 111111;
            userService.passwordResetByEmail(optionalUser.get(), verificationCode);
            emailService.sendEmailWithUserPassword(userDto.getEmail(), verificationCode);
            return ResponseEntity.ok("메일로 비밀번호가 보내졌습니다.");
        }
        return ResponseEntity.badRequest().body("아이디 또는 이메일이 존재하지 않습니다.");
    }
}
