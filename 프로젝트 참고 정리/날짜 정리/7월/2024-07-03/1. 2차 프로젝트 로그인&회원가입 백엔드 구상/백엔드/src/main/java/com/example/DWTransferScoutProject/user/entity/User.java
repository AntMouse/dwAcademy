package com.example.DWTransferScoutProject.user.entity;

import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import com.example.DWTransferScoutProject.common.account.entity.BaseAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements BaseAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplicationRoleEnum accountType;

    @Column(nullable = false, unique = true)
    private String accountId; // 사이트에서 사용하는 ID

    @Column(nullable = false)
    private String username; // 회원의 본명

    @JsonIgnore
    private String password;

    private String birthdate;
    private String gender;
    private String userEmail;
    private String contact;

    @Builder
    public User(ApplicationRoleEnum accountType, String accountId, String username, String password,
                String birthdate, String gender, String userEmail, String contact) {
        this.accountType = accountType;
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.userEmail = userEmail;
        this.contact = contact;
    }

    public void updateUserInfo(String username, String birthdate, String gender, String userEmail, String contact) {
        if (username != null) this.username = username;
        if (birthdate != null) this.birthdate = birthdate;
        if (gender != null) this.gender = gender;
        if (userEmail != null) this.userEmail = userEmail;
        if (contact != null) this.contact = contact;
    }

    public void updateUserPassword(String password) {
        if (password != null) {
            this.password = password;
        }
    }

    public void updateAccountType(ApplicationRoleEnum userType) {
        if (userType != null) {
            this.accountType = userType;
        }
    }

    @Override
    public String getAccountId() {
        return this.accountId;
    }

    @Override
    public ApplicationRoleEnum getAccountRole() {
        return this.accountType;
    }
}
