package com.example.DWTransferScoutProject.admin.entity;

import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import com.example.DWTransferScoutProject.common.account.entity.BaseAccount;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admins")
public class Admin implements BaseAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountId; // 기존 userId에서 변경

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String adminEmail;

    @Enumerated(value = EnumType.STRING)
    private ApplicationRoleEnum accountType;

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public ApplicationRoleEnum getAccountRole() {
        return accountType;
    }

    @Builder
    public Admin(ApplicationRoleEnum accountType, String accountId, String password, String adminEmail) {
        this.accountType = accountType;
        this.accountId = accountId;
        this.password = password;
        this.adminEmail = adminEmail;
    }

    public void updateAdminInfo(String adminEmail, ApplicationRoleEnum accountType) {
        if (adminEmail != null) {
            this.adminEmail = adminEmail;
        }
        if (accountType != null) {
            this.accountType = accountType;
        }
    }

    public void updateAdminPassword(String password) {
        if (password != null) {
            this.password = password;
        }
    }
}
