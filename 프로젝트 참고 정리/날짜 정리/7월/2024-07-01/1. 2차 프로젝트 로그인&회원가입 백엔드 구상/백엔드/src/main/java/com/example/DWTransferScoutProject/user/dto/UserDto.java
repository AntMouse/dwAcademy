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
    private String email;
    private String contact;
    private ApplicationRoleEnum userType;

    public UserDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.birthdate = user.getBirthdate();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.contact = user.getContact();
        this.userType = user.getUserType();
    }
}
