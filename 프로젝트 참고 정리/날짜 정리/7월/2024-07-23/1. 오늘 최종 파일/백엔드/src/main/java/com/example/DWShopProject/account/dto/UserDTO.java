package com.example.DWShopProject.account.dto;

import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.account.entity.User;
import com.example.DWShopProject.common.security.enums.UserRole;
import com.example.DWShopProject.account.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    // 전 계정 공용 필드
    private Long id;
    private String userId;
    private String email;
    private LocalDateTime registrationDate;
    private Set<UserRole> roles;

    // User 계정 전용 필드
    private String username;
    private Gender gender;
    private String birthdate;
    private String contact;
    private boolean isActive;
    private boolean isMarketingOptIn;
    private AddressDTO address;
}