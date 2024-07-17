package com.example.DWShopProject.account.entity;

import com.example.DWShopProject.common.security.enums.ApplicationRole;

import java.time.LocalDateTime;

public interface BaseAccount {
    // 고유 ID
    Long getId();
    
    // 계정 공통 값 get
    String getAccountId();
    String getPassword();
    ApplicationRole getAccountRole();
    LocalDateTime getRegistrationDate();

    // 계정 공통 값 update
    void updatePassword(String password);
}
