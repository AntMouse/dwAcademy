package com.example.DWShopProject.authentication.service;

import com.example.DWShopProject.account.common.dto.AccountRegistrationDTO;
import com.example.DWShopProject.account.common.service.AccountService;
import com.example.DWShopProject.account.dto.UserRegistrationDTO;
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

    @Transactional
    public <T extends AccountRegistrationDTO> T signUp(T accountRegistrationDto, AccountService<?, T> accountService) {
        if (accountRegistrationDto.getAccountId() == null || accountRegistrationDto.getPassword() == null || accountRegistrationDto.getEmail() == null) {
            throw new IllegalArgumentException("필수 필드가 누락되었습니다.");
        }

        if (accountService.findByAccountId(accountRegistrationDto.getAccountId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다: " + accountRegistrationDto.getAccountId());
        }

        if (!accountRegistrationDto.isPasswordMatching(accountRegistrationDto.getPassword(), accountRegistrationDto.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(accountRegistrationDto.getPassword());

        T encodedAccountRegistrationDto = (T) ((UserRegistrationDTO) accountRegistrationDto).toBuilder()
                .password(encodedPassword)
                .build();

        return accountService.saveAccount(encodedAccountRegistrationDto);
    }
}