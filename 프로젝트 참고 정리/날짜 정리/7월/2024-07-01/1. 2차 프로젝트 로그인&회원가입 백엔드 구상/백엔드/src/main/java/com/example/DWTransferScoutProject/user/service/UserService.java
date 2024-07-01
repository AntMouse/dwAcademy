package com.example.DWTransferScoutProject.user.service;

import com.example.DWTransferScoutProject.user.dto.UserDto;
import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.user.repository.UserRepository;
import com.example.DWTransferScoutProject.auth.security.JwtUtil;
import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

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

        return userRepository.save(user);
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 사용자 정보 업데이트
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (userDto.getUserId() != null) {
            existingUser.setUserId(userDto.getUserId());
        }
        if (userDto.getUsername() != null) {
            existingUser.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if (userDto.getBirthdate() != null) {
            existingUser.setBirthdate(userDto.getBirthdate());
        }
        if (userDto.getGender() != null) {
            existingUser.setGender(userDto.getGender());
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getContact() != null) {
            existingUser.setContact(userDto.getContact());
        }
        if (userDto.getUserType() != null) {
            existingUser.setUserType(userDto.getUserType());
        }

        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }

    // 사용자 조회
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public UserDto mapToDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(null)
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .email(user.getEmail())
                .contact(user.getContact())
                .userType(user.getUserType())
                .build();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 사용자 프로필 업데이트
    public UserDto edit(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if (userDto.getBirthdate() != null) {
            user.setBirthdate(userDto.getBirthdate());
        }
        if (userDto.getGender() != null) {
            user.setGender(userDto.getGender());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getContact() != null) {
            user.setContact(userDto.getContact());
        }

        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }

}
