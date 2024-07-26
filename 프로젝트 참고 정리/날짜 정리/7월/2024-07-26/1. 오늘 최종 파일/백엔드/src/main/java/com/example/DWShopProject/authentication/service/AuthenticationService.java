package com.example.DWShopProject.authentication.service;

import com.example.DWShopProject.address.converter.AddressDTOConverter;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.address.repository.AddressRepository;
import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.cart.repository.CartRepository;
import com.example.DWShopProject.cart.service.CartService;
import com.example.DWShopProject.common.security.enums.UserRole;
import com.example.DWShopProject.user.converter.UserDTOConverter;
import com.example.DWShopProject.user.dto.UserDTO;
import com.example.DWShopProject.user.dto.UserRegistrationDTO;
import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.user.repository.UserRepository;
import com.example.DWShopProject.common.exception.UserAlreadyExistsException;
import com.example.DWShopProject.user.service.UserService;
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
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final AddressDTOConverter addressDtoConverter;
    private final UserService userService;

    public UserDTO registerUser(UserRegistrationDTO userRegDto) {
        if (userRepository.existsByUserId(userRegDto.getUserId())) {
            throw new UserAlreadyExistsException("User ID already exists");
        }

        if (userRepository.existsByEmail(userRegDto.getEmail())) {
            throw new UserAlreadyExistsException("User email already exists");
        }

        if (!userRegDto.isPasswordMatching()) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        String encodedPassword = passwordEncoder.encode(userRegDto.getPassword());
        UserRegistrationDTO encodedUserRegDto = userRegDto.withEncodedPassword(encodedPassword);

        Address address = addressDtoConverter.convertToAddressEntity(userRegDto.getAddress());
        Address savedAddress = addressRepository.save(address);

        User user = userDtoConverter.convertToUserRegistrationEntity(encodedUserRegDto);
        User userWithAddress = userService.buildUserWithAddress(user, savedAddress);

        userWithAddress.updateUserRoles(new HashSet<>(Collections.singletonList(UserRole.USER)));

        User savedUser = userRepository.save(userWithAddress);
        log.info("New user registered: {}", savedUser.getUserId());

        Cart cart = cartService.createCartForUser(savedUser);
        cartRepository.save(cart);

        return userDtoConverter.convertToUserDto(savedUser);
    }
}