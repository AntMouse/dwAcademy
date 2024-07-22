package com.example.DWTransferScoutProject.auth.service;

import com.example.DWTransferScoutProject.common.account.dto.LoginDto;
import com.example.DWTransferScoutProject.superadmin.repository.SuperAdminRepository;
import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.user.repository.UserRepository;
import com.example.DWTransferScoutProject.auth.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final SuperAdminRepository superAdminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String login(LoginDto loginDto, HttpServletResponse response) {
        log.debug("Login attempt with accountId: {}", loginDto.getAccountId()); // 로그인 시도 로그 추가
        log.debug("LoginDto contents: accountId={}, password={}", loginDto.getAccountId(), loginDto.getPassword()); // 추가된 부분

        Optional<User> optionalUser = userRepository.findByAccountId(loginDto.getAccountId());

        if (optionalUser.isEmpty()) {
            log.error("회원이 존재하지 않음: {}", loginDto.getAccountId()); // 수정된 부분: 예외 로깅
            throw new IllegalArgumentException("회원이 존재하지 않음");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            log.error("비밀번호가 일치하지 않음: {}", loginDto.getAccountId()); // 수정된 부분: 예외 로깅
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(user.getAccountId(), user.getAccountType());

        return token;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByUsernameAndEmail(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email);
    }

    public List<User> findUsersByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    public void passwordResetByEmail(User user, int verificationCode) {
        user.updatePassword(passwordEncoder.encode(String.valueOf(verificationCode)));
        userRepository.save(user);
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByAccountId(userId);
    }
}
