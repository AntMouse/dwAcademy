package com.example.DWTransferScoutProject.user.dto;

import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import com.example.DWTransferScoutProject.user.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String userId; // 사이트에서 사용하는 ID
    private String username; // 회원의 본명
    private String password;
    private String birthdate;
    private String gender;
    private String userEmail;
    private String contact;
    private ApplicationRoleEnum accountType;

    public UserDto(User user) {
        this.id = user.getId();
        this.userId = user.getAccountId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.birthdate = user.getBirthdate();
        this.gender = user.getGender();
        this.userEmail = user.getUserEmail();
        this.contact = user.getContact();
        this.accountType = user.getAccountType();
    }
}
