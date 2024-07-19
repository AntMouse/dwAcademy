package com.example.DWShopProject.account.common.dto;

public interface AccountRegistrationDTO {
    // 계정 공통 값 get
    String getAccountId();
    String getPassword();
    String getConfirmPassword();
    String getEmail();

    default boolean isPasswordMatching(String password, String confirmPassword) {
        return password != null && !password.isEmpty() && password.equals(confirmPassword);
    }
}
