package com.example.DWShopProject.authentication.service;

import com.example.DWShopProject.account.common.util.PasswordUtils;
import com.example.DWShopProject.account.dto.LoginDTO;
import com.example.DWShopProject.account.dto.UserRegistrationDTO;
import com.example.DWShopProject.account.entity.User;
import com.example.DWShopProject.account.repository.UserRepository;
import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.common.exception.UserAlreadyExistsException;
import com.example.DWShopProject.common.security.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.existsByUserId(userRegistrationDTO.getUserId())) {
            throw new UserAlreadyExistsException("User ID already exists");
        }
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        if (!PasswordUtils.isPasswordMatching(userRegistrationDTO.getPassword(), userRegistrationDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        AddressDTO addressDTO = userRegistrationDTO.getAddress();
        Address address = Address.builder()
                .postalCode(addressDTO.getPostalCode())
                .primaryAddress(addressDTO.getPrimaryAddress())
                .detailAddress(addressDTO.getDetailAddress())
                .build();

        Set<UserRole> roles = new HashSet<>(userRegistrationDTO.getRoles());

        User user = User.builder()
                .userId(userRegistrationDTO.getUserId())
                .password(passwordEncoder.encode(userRegistrationDTO.getPassword()))
                .email(userRegistrationDTO.getEmail())
                .roles(roles)
                .username(userRegistrationDTO.getUsername())
                .gender(userRegistrationDTO.getGender())
                .birthdate(userRegistrationDTO.getBirthdate())
                .contact(userRegistrationDTO.getContact())
                .isMarketingOptIn(userRegistrationDTO.isMarketingOptIn())
                .address(address)
                .build();

        return userRepository.save(user);
    }
}