package com.example.DWShopProject.user.dto;

import com.example.DWShopProject.user.util.PasswordUtils;
import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.common.security.enums.UserRole;
import com.example.DWShopProject.user.enums.Gender;
import lombok.*;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDTO {
    // 전 계정 공용 필드
    private String userId;
    private String password;
    private String confirmPassword;
    private String email;
    private Set<UserRole> roles;

    // User 계정 전용 필드
    private String username;
    private Gender gender;
    private String birthdate;
    private String contact;
    private boolean isMarketingOptIn;
    private AddressDTO address;

    public boolean isPasswordMatching() {
        return PasswordUtils.isPasswordMatching(password, confirmPassword);
    }

    public UserRegistrationDTO withEncodedPassword(String encodedPassword) {
        return UserRegistrationDTO.builder()
                .userId(this.userId)
                .password(encodedPassword)
                .confirmPassword(this.confirmPassword)
                .email(this.email)
                .roles(this.roles)
                .username(this.username)
                .gender(this.gender)
                .birthdate(this.birthdate)
                .contact(this.contact)
                .isMarketingOptIn(this.isMarketingOptIn)
                .address(this.address)
                .build();
    }
}
