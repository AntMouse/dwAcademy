package com.example.DWShopProject.authentication.service;

import com.example.DWShopProject.account.dto.BaseAccountDto;
import com.example.DWShopProject.account.service.BaseAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public <T extends BaseAccountDto> T signUp(T accountDto, BaseAccountService<?, T> accountService) {
        if (accountDto.getAccountId() == null || accountDto.getPassword() == null || accountDto.getEmail() == null) {
            throw new IllegalArgumentException("필수 필드가 누락되었습니다.");
        }

        if (accountService.findByAccountId(accountDto.getAccountId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다: " + accountDto.getAccountId());
        }

        if (accountDto.getPassword() != null && !accountDto.getPassword().equals(accountDto.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(accountDto.getPassword());
        accountDto.setPassword(encodedPassword);

        return accountService.saveAccount(accountDto);
    }
}
