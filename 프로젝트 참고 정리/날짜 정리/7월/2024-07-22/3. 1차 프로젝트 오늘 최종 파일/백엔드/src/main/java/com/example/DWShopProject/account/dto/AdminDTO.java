package com.example.DWShopProject.account.dto;

import com.example.DWShopProject.account.common.dto.AccountDTO;
import com.example.DWShopProject.common.security.enums.ApplicationRole;
import com.example.DWShopProject.account.entity.Admin;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDTO implements AccountDTO {
    // 전 계정 공용 필드
    private Long id;
    private String accountId;
    private String email;
    private LocalDateTime registrationDate;

    // Admin 계정 전용 필드
    private Set<ApplicationRole> accountRoles;

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.accountId = admin.getAccountId();
        this.email = admin.getEmail();
        this.registrationDate = admin.getRegistrationDate();

        this.accountRoles = admin.getAccountRoles();
    }
}