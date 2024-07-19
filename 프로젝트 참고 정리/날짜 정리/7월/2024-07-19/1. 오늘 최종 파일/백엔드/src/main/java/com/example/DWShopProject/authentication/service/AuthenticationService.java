package com.example.DWShopProject.authentication.service;

import com.example.DWShopProject.account.common.dto.AccountDTO;
import com.example.DWShopProject.account.common.dto.AccountRegistrationDTO;
import com.example.DWShopProject.account.common.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public <T extends AccountRegistrationDTO> T signUp(T accountRegistrationDto, AccountService<?, T> accountService) {
        // 필수 필드 검증
        if (accountRegistrationDto.getAccountId() == null || accountRegistrationDto.getPassword() == null || accountRegistrationDto.getEmail() == null) {
            throw new IllegalArgumentException("필수 필드가 누락되었습니다.");
        }

        // 중복 아이디 검증
        if (accountService.findByAccountId(accountRegistrationDto.getAccountId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다: " + accountRegistrationDto.getAccountId());
        }

        // 비밀번호 확인 검증
        if (!accountRegistrationDto.isPasswordMatching(accountRegistrationDto.getPassword(), accountRegistrationDto.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        // 비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(accountRegistrationDto.getPassword());

        // 계정 생성 및 저장
        T encodedAccountRegistrationDto = (T) accountRegistrationDto.toBuilder()
                .password(encodedPassword)
                .build();

        return accountService.saveAccount(encodedAccountRegistrationDto);
    }
}
