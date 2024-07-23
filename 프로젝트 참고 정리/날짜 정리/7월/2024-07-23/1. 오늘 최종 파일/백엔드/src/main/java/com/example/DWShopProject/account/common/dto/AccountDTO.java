package com.example.DWShopProject.account.common.dto;

import com.example.DWShopProject.account.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserDTO.class, name = "user"),
        @JsonSubTypes.Type(value = AdminDTO.class, name = "admin")
})
public interface AccountDTO {
    // 고유 ID
    Long getId();

    // 계정 공통 값 get
    String getUserId();
    String getEmail();
    LocalDateTime getRegistrationDate();
}