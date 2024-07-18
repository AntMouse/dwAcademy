package com.example.DWShopProject.account.entity;

import com.example.DWShopProject.common.security.enums.ApplicationRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admins")
public class Admin implements BaseAccount {
    // 전 계정 공용 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountId;

    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    @ElementCollection(targetClass = ApplicationRole.class, fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<ApplicationRole> accountRole;

    private LocalDateTime registrationDate;

    // 생성자 & 메서드
    public Admin(String accountId, String password, String email, Set<ApplicationRole> accountRole) {
        this.accountId = accountId;
        this.password = password;
        this.email = email;
        this.accountRole = accountRole;
        this.registrationDate = LocalDateTime.now(); // 기본값 설정이 있어서 서비스에서 따로 처리 안 함
    }

    public void updateAdminProfile(String email, String password) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
    }

    @Override
    public void updatePassword(String password) {
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
    }

    public void updateAccountRoles(Set<ApplicationRole> accountRoles) {
        if (accountRoles != null && !accountRoles.isEmpty()) {
            this.accountRole.addAll(accountRoles);
        }
    }
}
