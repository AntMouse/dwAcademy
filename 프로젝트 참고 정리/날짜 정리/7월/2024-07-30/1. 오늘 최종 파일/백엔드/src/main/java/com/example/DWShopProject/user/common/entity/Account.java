package com.example.DWShopProject.user.common.entity;

import java.time.LocalDateTime;

public interface Account {
    // 고유 ID
    Long getId();
    
    // 계정 공통 값 get
    String getAccountId();
    String getPassword();
    String getEmail();
    LocalDateTime getRegistrationDate();

    // 계정 공통 값 update
    void updatePassword(String password);
}
