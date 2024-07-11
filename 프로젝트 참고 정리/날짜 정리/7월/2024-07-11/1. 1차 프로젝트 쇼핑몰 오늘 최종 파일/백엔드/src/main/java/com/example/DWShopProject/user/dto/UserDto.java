package com.example.DWShopProject.user.dto;

import com.example.DWShopProject.account.dto.BaseAccountDto;
import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.security.enums.ApplicationRole;
import com.example.DWShopProject.user.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements BaseAccountDto {
    // 전 계정 공용 필드
    private Long id;
    private String accountId;
    private String password;
    // 아래 필드 1개는 전 계정 공용 필드 예외 (단순히 비밀번호 확인만 하는 용도이기 때문에 엔티티에 없음)
    private String confirmPassword;
    private String email;
    private ApplicationRole accountRole;
    private LocalDateTime registrationDate;

    // User 계정 전용 필드
    private String username;
    private Gender gender;
    private String birthdate;
    private String contact;
    private boolean isActive;
    private boolean isMarketingOptIn;

    public UserDto(User user) {
        this.id = user.getId();
        this.accountId = user.getAccountId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.accountRole = user.getAccountRole();
        this.registrationDate = user.getRegistrationDate();

        this.username = user.getUsername();
        this.gender = user.getGender();
        this.birthdate = user.getBirthdate();
        this.contact = user.getContact();
        this.isActive = user.isActive();
        this.isMarketingOptIn = user.isMarketingOptIn();
    }
}