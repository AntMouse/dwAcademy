package com.example.DWShopProject.user.service;

import com.example.DWShopProject.user.converter.UserDTOConverter;
import com.example.DWShopProject.user.dto.UserDTO;
import com.example.DWShopProject.user.dto.UserPasswordUpdateDTO;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import com.example.DWShopProject.cart.repository.CartRepository;
import com.example.DWShopProject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final UserDTOConverter userDtoConverter;

    public User buildUserWithAddress(User user, Address address) {
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .registrationDate(user.getRegistrationDate())
                .roles(user.getRoles())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .birthdate(user.getBirthdate())
                .contact(user.getContact())
                .isActive(user.isActive())
                .isMarketingOptIn(user.isMarketingOptIn())
                .address(address)
                .build();
    }

    public UserDTO updateUserPassword(UserPasswordUpdateDTO userPassDto, User existingUser) {
        if (userPassDto.isNewPasswordMatching()) {
            String encodedPassword = passwordEncoder.encode(userPassDto.getNewPassword());
            existingUser.updateUserPassword(encodedPassword);
            User savedUser = userRepository.save(existingUser);
            return userDtoConverter.convertToUserDto(savedUser); // 엔티티를 DTO로 변환하여 반환
        } else {
            throw new IllegalArgumentException("New passwords do not match");
        }
    }


















//    // 사용자 정보 업데이트
//    public UserDTO updateUser(Long id, UserDTO userDto) {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        // 엔티티의 업데이트 메서드를 사용하여 정보 변경
//        existingUser.updateUserProfile(
//                userDto.getEmail(),
//                userDto.getFullName(),
//                userDto.getGender(),
//                userDto.getBirthdate(),
//                userDto.getContact()
//        );
//
//        if (userDto.getRoles() != null) {
//            existingUser.updateUserRoles(userDto.getRoles());
//        }
//
//        User updatedUser = userRepository.save(existingUser);
//        return mapToDTO(updatedUser);
//    }





    // 비밀번호 재설정
    public void passwordResetByEmail(User user, int verificationCode) {
        user.updateUserPassword(passwordEncoder.encode(String.valueOf(verificationCode)));
        userRepository.save(user);
    }








//    // mypage 수정 완료
//    public User edit(UserDTO userDto) {
//        User existingUser = userRepository.findById(userDto.getId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        existingUser.updateUserProfile(userDto.getEmail(), userDto.getFullName(), userDto.getGender(), userDto.getBirthdate(), userDto.getContact());
//
//        return userRepository.save(existingUser);
//    }
//
//    // UserDTO를 User 객체로 변환하는 메서드
//    public UserDTO AdminupdateUser(Long id, UserDTO userDto) {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        // 회원 타입 업데이트
//        if (userDto.getRoles() != null) {
//            existingUser.updateUserRoles(userDto.getRoles());
//        }
//
//        User updatedUser = userRepository.save(existingUser);
//        return mapToDTO(updatedUser);
//    }
}
