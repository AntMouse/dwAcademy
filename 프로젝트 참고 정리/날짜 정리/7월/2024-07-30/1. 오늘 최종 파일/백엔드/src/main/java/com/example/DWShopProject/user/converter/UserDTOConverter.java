package com.example.DWShopProject.user.converter;

import com.example.DWShopProject.address.converter.AddressDTOConverter;
import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.user.dto.UserDTO;
import com.example.DWShopProject.user.dto.UserRegistrationDTO;
import com.example.DWShopProject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserDTOConverter {

    private final AddressDTOConverter addressDtoConverter;

    public UserDTO convertToUserDto(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .registrationDate(user.getRegistrationDate())
                .roles(user.getRoles())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .birthdate(user.getBirthdate())
                .contact(user.getContact())
                .isActive(user.isActive())
                .isMarketingOptIn(user.isMarketingOptIn())
                .build();
    }

    public User convertToUserEntity(UserDTO userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .registrationDate(userDto.getRegistrationDate())
                .roles(userDto.getRoles())
                .fullName(userDto.getFullName())
                .gender(userDto.getGender())
                .birthdate(userDto.getBirthdate())
                .contact(userDto.getContact())
                .isActive(userDto.isActive())
                .isMarketingOptIn(userDto.isMarketingOptIn())
                .build();
    }

    public User convertToUserRegistrationEntity(UserRegistrationDTO userRegDto) {
        return User.builder()
                .username(userRegDto.getUsername())
                .password(userRegDto.getPassword())
                .email(userRegDto.getEmail())
                .registrationDate(LocalDateTime.now()) // 기본값 설정
                .roles(userRegDto.getRoles())
                .fullName(userRegDto.getFullName())
                .gender(userRegDto.getGender())
                .birthdate(userRegDto.getBirthdate())
                .contact(userRegDto.getContact())
                .isActive(true) // 기본값 설정
                .isMarketingOptIn(userRegDto.isMarketingOptIn())
                .build();
    }
}
