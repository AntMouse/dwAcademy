package com.example.DWShopProject.account.dto;

import com.example.DWShopProject.account.common.dto.AccountRegistrationDTO;
import com.example.DWShopProject.common.security.enums.ApplicationRole;
import lombok.*;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRegistrationDTO implements AccountRegistrationDTO {
    private String accountId;
    private String password;
    private String confirmPassword;
    private String email;

    // Admin 계정 전용 필드
    private Set<ApplicationRole> accountRoles;
}
