package com.example.DWShopProject.account.dto;

import com.example.DWShopProject.account.common.dto.AccountRegistrationDTO;
import com.example.DWShopProject.address.dto.AddressDto;
import com.example.DWShopProject.common.security.enums.ApplicationRole;
import com.example.DWShopProject.account.enums.Gender;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDTO implements AccountRegistrationDTO {
    // 전 계정 공용 필드
    private String accountId;
    private String password;
    private String confirmPassword;
    private String email;

    // User 계정 전용 필드
    private ApplicationRole accountRole;
    private String username;
    private Gender gender;
    private String birthdate;
    private String contact;
    private boolean isMarketingOptIn;
    private AddressDto address;
}
