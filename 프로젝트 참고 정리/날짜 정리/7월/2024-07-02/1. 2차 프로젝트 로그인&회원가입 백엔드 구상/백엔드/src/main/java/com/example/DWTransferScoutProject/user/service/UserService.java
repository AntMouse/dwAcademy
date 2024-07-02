package com.example.DWTransferScoutProject.user.service;

import com.example.DWTransferScoutProject.common.account.service.BaseAccountService;
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
public class UserService implements BaseAccountService<User, UserDto> {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUp(UserDto userDto) {
        // 빌더 패턴을 사용하여 엔티티 객체 생성
        User user = User.builder()
                .accountType(ApplicationRoleEnum.USER)
                .accountId(userDto.getUserId())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .birthdate(userDto.getBirthdate())
                .gender(userDto.getGender())
                .userEmail(userDto.getUserEmail()) // 수정된 부분
                .contact(userDto.getContact())
                .build();

        // 이미 존재하는 아이디인지 확인
        if (userRepository.findByAccountId(user.getAccountId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다: " + user.getAccountId());
        }

        // 엔티티 저장
        return userRepository.save(user);
    }

    @Override
    public void deleteAccount(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateAccount(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 엔티티의 업데이트 메서드를 사용하여 정보 변경
        existingUser.updateUserInfo(
                userDto.getUsername(),
                userDto.getBirthdate(),
                userDto.getGender(),
                userDto.getUserEmail(),
                userDto.getContact()
        );

        if (userDto.getAccountType() != null) {
            existingUser.updateAccountType(userDto.getAccountType());
        }

        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }

    public UserDto edit(UserDto userDto) {
        return updateAccount(userDto.getId(), userDto);
    }

    public void updateUserPassword(Long id, String newPassword) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.updateUserPassword(passwordEncoder.encode(newPassword));
        userRepository.save(existingUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDto mapToDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userId(user.getAccountId())
                .username(user.getUsername())
                .password(null)
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .userEmail(user.getUserEmail())
                .contact(user.getContact())
                .accountType(user.getAccountType())
                .build();
    }

    @Override
    public Optional<User> findByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
