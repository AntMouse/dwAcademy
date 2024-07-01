package com.example.DWTransferScoutProject.auth.service;

import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import com.example.DWTransferScoutProject.user.dto.LoginDto;
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

    // 로그인
    @Transactional
    public String login(LoginDto loginDto, HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.findByUserId(loginDto.getUserId());

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("회원이 존재하지 않음");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(user.getUserId(), user.getUserType());

        return token; // 토큰 반환
    }

    // 사용자 ID로 사용자 찾기
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 회원가입
    public User signUp(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setBirthdate(userDto.getBirthdate());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        user.setContact(userDto.getContact());
        user.setUserType(ApplicationRoleEnum.USER);

        if (userRepository.findByUserId(user.getUserId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다: " + user.getUserId());
        }

        userRepository.save(user);

        return user;
    }

    // 회원가입 이메일 찾기 메소드
    public Optional<User> findUserByUsernameAndEmail(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email);
    }

    // 이메일로 유저 목록 찾기
    public List<User> findUsersByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    // 비밀번호 초기화
    public void passwordResetByEmail(User user, int verificationCode) {
        user.setPassword(passwordEncoder.encode(String.valueOf(verificationCode)));
        userRepository.save(user);
    }

    // 사용자 ID로 사용자 찾기 (AuthController에서 사용하는 메서드)
    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
