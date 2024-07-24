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

    public UserDTO convertToDto(User user) {
        AddressDTO addressDto = addressDtoConverter.convertToDto(user.getAddress());
        return UserDTO.builder()
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
                .address(addressDto)
                .build();
    }

    public User convertToEntity(UserDTO userDto) {
        Address address = addressDtoConverter.convertToEntity(userDto.getAddress());
        return User.builder()
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
        Address address = addressDtoConverter.convertToEntity(userRegDto.getAddress());
        return User.builder()
                .userId(userRegDto.getUserId())
                .password(userRegDto.getPassword())
                .email(userRegDto.getEmail())
                .registrationDate(LocalDateTime.now()) // 기본값 설정
                .roles(userRegDto.getRoles())
                .username(userRegDto.getUsername())
                .gender(userRegDto.getGender())
                .birthdate(userRegDto.getBirthdate())
                .contact(userRegDto.getContact())
                .isActive(true) // 기본값 설정
                .isMarketingOptIn(userRegDto.isMarketingOptIn())
                .address(address)
                .build();
    }
}
