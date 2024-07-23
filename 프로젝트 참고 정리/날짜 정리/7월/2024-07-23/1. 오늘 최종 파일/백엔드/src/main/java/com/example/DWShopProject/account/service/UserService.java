package com.example.DWShopProject.account.service;

import com.example.DWShopProject.account.dto.UserDTO;
import com.example.DWShopProject.account.dto.UserPasswordUpdateDTO;
import com.example.DWShopProject.account.dto.UserRegistrationDTO;
import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.address.service.AddressService;
import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.account.entity.User;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import com.example.DWShopProject.cart.repository.CartRepository;
import com.example.DWShopProject.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final AddressService addressService;

    public UserDTO convertToDTO(User user) {
        AddressDTO addressDTO = addressService.convertToDTO(user.getAddress());
        return UserDTO.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .email(user.getEmail())
                .registrationDate(user.getRegistrationDate())
                .roles(user.getRoles())
                .username(user.getUsername())
                .gender(user.getGender())
                .birthdate(user.getBirthdate())
                .contact(user.getContact())
                .isActive(user.isActive())
                .isMarketingOptIn(user.isMarketingOptIn())
                .address(addressDTO)
                .build();
    }

    public User convertToEntity(UserDTO userDto) {
        Address address = addressService.convertToEntity(userDto.getAddress());
        return User.builder()
                .id(userDto.getId())
                .userId(userDto.getUserId())
                .email(userDto.getEmail())
                .registrationDate(userDto.getRegistrationDate())
                .roles(userDto.getRoles())
                .username(userDto.getUsername())
                .gender(userDto.getGender())
                .birthdate(userDto.getBirthdate())
                .contact(userDto.getContact())
                .isActive(userDto.isActive())
                .isMarketingOptIn(userDto.isMarketingOptIn())
                .address(address)
                .build();
    }

    public User convertToEntity(UserRegistrationDTO userRegDto) {
        Address address = addressService.convertToEntity(userRegDto.getAddress());
        return new User(
                userRegDto.getUserId(),
                userRegDto.getPassword(),
                userRegDto.getEmail(),
                userRegDto.getRoles(),
                userRegDto.getUsername(),
                userRegDto.getGender(),
                userRegDto.getBirthdate(),
                userRegDto.getContact(),
                userRegDto.isMarketingOptIn(),
                address
        );
    }

    public User convertToEntity(UserPasswordUpdateDTO userPassDto, User existingUser) {
        if (userPassDto.isNewPasswordMatching()) {
            existingUser.updateUserPassword(userPassDto.getNewPassword());
        } else {
            throw new IllegalArgumentException("New passwords do not match");
        }
        return existingUser;
    }





















    public UserRegistrationDTO createUser(UserRegistrationDTO userDto) {
        User user = User.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword()) // 공용 회원가입에서 인코딩 되었음
                .email(userDto.getEmail())
                .roles(userDto.getRoles())
                .username(userDto.getUsername())
                .gender(userDto.getGender())
                .birthdate(userDto.getBirthdate())
                .contact(userDto.getContact())
                .isMarketingOptIn(userDto.isMarketingOptIn())
                .build();

        User savedUser = userRepository.save(user);

        // 사용자에 대한 장바구니 생성
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);

        return UserRegistrationDTO.builder() // 추가: UserRegistrationDTO 리턴
                .userId(savedUser.getUserId())
                .password(savedUser.getPassword())
                .email(savedUser.getEmail())
                .roles(savedUser.getRoles())
                .username(savedUser.getUsername())
                .gender(savedUser.getGender())
                .birthdate(savedUser.getBirthdate())
                .contact(savedUser.getContact())
                .isMarketingOptIn(savedUser.isMarketingOptIn())
                .address(new AddressDTO(savedUser.getAddress()))
                .build();
    }

    // 사용자 정보 업데이트
    public UserDTO updateUser(Long id, UserDTO userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 엔티티의 업데이트 메서드를 사용하여 정보 변경
        existingUser.updateUserProfile(
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getGender(),
                userDto.getBirthdate(),
                userDto.getContact()
        );

        if (userDto.getRoles() != null) {
            existingUser.updateUserRoles(userDto.getRoles());
        }

        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }

    // User 엔티티를 UserDTO로 변환
    public UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .roles(user.getRoles())
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(null) // 비밀번호 필드를 null로 설정
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .email(user.getEmail())
                .contact(user.getContact())
                .isActive(user.isActive())
                .isMarketingOptIn(user.isMarketingOptIn())
                .registrationDate(user.getRegistrationDate())
                .build();
    }



    // 비밀번호 재설정
    public void passwordResetByEmail(User user, int verificationCode) {
        user.updateUserPassword(passwordEncoder.encode(String.valueOf(verificationCode)));
        userRepository.save(user);
    }








    // mypage 수정 완료
    public User edit(UserDTO userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.updateUserProfile(userDto.getEmail(), userDto.getUsername(), userDto.getGender(), userDto.getBirthdate(), userDto.getContact());

        return userRepository.save(existingUser);
    }

    // UserDTO를 User 객체로 변환하는 메서드
    public UserDTO AdminupdateUser(Long id, UserDTO userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 회원 타입 업데이트
        if (userDto.getRoles() != null) {
            existingUser.updateUserRoles(userDto.getRoles());
        }

        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }
}
