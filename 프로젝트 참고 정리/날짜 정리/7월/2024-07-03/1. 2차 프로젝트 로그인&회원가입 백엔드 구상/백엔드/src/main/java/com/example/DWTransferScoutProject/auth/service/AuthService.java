package com.example.DWTransferScoutProject.auth.service;

import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import com.example.DWTransferScoutProject.common.account.dto.LoginDto;
import com.example.DWTransferScoutProject.user.dto.UserDto;
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

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String login(LoginDto loginDto, HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.findByAccountId(loginDto.getAccountId());

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("회원이 존재하지 않음");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(user.getAccountId(), user.getAccountType());

        return token;
    }

    public User signUp(UserDto userDto) {
        // 빌더 패턴을 사용하여 User 객체 생성
        User user = User.builder()
                .accountId(userDto.getUserId())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .birthdate(userDto.getBirthdate())
                .gender(userDto.getGender())
                .userEmail(userDto.getUserEmail()) // 수정된 부분
                .contact(userDto.getContact())
                .accountType(ApplicationRoleEnum.USER)
                .build();

        // 이미 존재하는 아이디인지 확인
        if (userRepository.findByAccountId(user.getAccountId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다: " + user.getAccountId());
        }

        // 엔티티 저장
        userRepository.save(user);

        return user;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByUsernameAndEmail(String username, String email) {
        return userRepository.findByUsernameAndUserEmail(username, email);
    }

    public List<User> findUsersByEmail(String email) {
        return userRepository.findAllByUserEmail(email);
    }

    public void passwordResetByEmail(User user, int verificationCode) {
        user.updateUserPassword(passwordEncoder.encode(String.valueOf(verificationCode)));
        userRepository.save(user);
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByAccountId(userId);
    }
}
