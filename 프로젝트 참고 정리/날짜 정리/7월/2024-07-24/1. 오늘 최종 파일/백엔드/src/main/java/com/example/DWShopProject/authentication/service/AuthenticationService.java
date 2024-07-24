package com.example.DWShopProject.authentication.service;

import com.example.DWShopProject.common.security.enums.UserRole;
import com.example.DWShopProject.user.converter.UserDTOConverter;
import com.example.DWShopProject.user.dto.UserDTO;
import com.example.DWShopProject.user.dto.UserRegistrationDTO;
import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.user.repository.UserRepository;
import com.example.DWShopProject.common.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDTOConverter userDtoConverter;

    public UserDTO registerUser(UserRegistrationDTO userRegDto) {
        if (userRepository.existsByUserId(userRegDto.getUserId()) ||
            userRepository.existsByEmail(userRegDto.getEmail())) {
            throw new UserAlreadyExistsException("User ID or email already exists");
        }

        if (!userRegDto.isPasswordMatching()) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        String encodedPassword = passwordEncoder.encode(userRegDto.getPassword());
        UserRegistrationDTO encodedUserRegDto = userRegDto.withEncodedPassword(encodedPassword);

        User user = userDtoConverter.convertToEntity(encodedUserRegDto);
        user.updateUserRoles(new HashSet<>(Collections.singletonList(UserRole.USER)));

        User savedUser = userRepository.save(user);
        log.info("New user registered: {}", savedUser.getUserId());

        return userDtoConverter.convertToDto(savedUser);
    }
}