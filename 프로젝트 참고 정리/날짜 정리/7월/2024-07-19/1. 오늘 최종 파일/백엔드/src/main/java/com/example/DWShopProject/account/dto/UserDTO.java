package com.example.DWShopProject.account.dto;

import com.example.DWShopProject.account.common.dto.AccountDTO;
import com.example.DWShopProject.address.dto.AddressDto;
import com.example.DWShopProject.account.entity.User;
import com.example.DWShopProject.common.security.enums.ApplicationRole;
import com.example.DWShopProject.account.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements AccountDTO {
    // 전 계정 공용 필드
    private Long id;
    private String accountId;
    private String email;
    private LocalDateTime registrationDate;

    // User 계정 전용 필드
    private ApplicationRole accountRole;
    private String username;
    private Gender gender;
    private String birthdate;
    private String contact;
    private boolean isActive;
    private boolean isMarketingOptIn;
    private AddressDto address;

    public UserDTO(User user) {
        this.id = user.getId();
        this.accountId = user.getAccountId();
        this.email = user.getEmail();
        this.registrationDate = user.getRegistrationDate();

        this.accountRole = user.getAccountRole();
        this.username = user.getUsername();
        this.gender = user.getGender();
        this.birthdate = user.getBirthdate();
        this.contact = user.getContact();
        this.isActive = user.isActive();
        this.isMarketingOptIn = user.isMarketingOptIn();
        this.address = user.getAddress() != null ? new AddressDto(user.getAddress()) : null;
    }
}