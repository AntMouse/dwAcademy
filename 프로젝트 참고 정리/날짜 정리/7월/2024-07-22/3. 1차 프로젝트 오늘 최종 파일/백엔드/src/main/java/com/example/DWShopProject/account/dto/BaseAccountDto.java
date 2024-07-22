package com.example.DWShopProject.account.dto;

import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true) // 수정: toBuilder 추가
public abstract class BaseAccountDto {
    private Long id;
    private String accountId;
    private String password;
    private String confirmPassword;
    private String email;
    private LocalDateTime registrationDate;
}
