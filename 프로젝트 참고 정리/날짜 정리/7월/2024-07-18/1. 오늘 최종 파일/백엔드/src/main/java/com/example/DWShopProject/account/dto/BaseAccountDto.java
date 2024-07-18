package com.example.DWShopProject.account.dto;

import com.example.DWShopProject.common.security.enums.ApplicationRole;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserDto.class, name = "user"),
})
public interface BaseAccountDto {
    // 고유 ID
    Long getId();

    // 계정 공통 값 get
    String getAccountId();
    String getPassword();
    String getConfirmPassword();
    String getEmail();
    ApplicationRole getAccountRole();

    // 계정 공통 값 set (DTO라 set 사용)

    void setPassword(String password);
    void setConfirmPassword(String confirmPassword);
    void setEmail(String email);
    void setAccountRole(ApplicationRole accountRole);
}