package com.example.DWShopProject.account.dto;

import com.example.DWShopProject.common.security.enums.ApplicationRole;
import com.example.DWShopProject.account.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDto implements BaseAccountDto {
    // 전 계정 공용 필드. 단, 비밀번호 확인은 제외(단순히 비밀번호 확인만 하는 용도이기 때문에 엔티티에 없음).
    private Long id;
    private String accountId;
    private String password;
    private String confirmPassword;
    private String email;
    private ApplicationRole accountRole;
    private LocalDateTime registrationDate;

    public AdminDto(User user) {
        this.id = user.getId();
        this.accountId = user.getAccountId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.accountRole = user.getAccountRole();
        this.registrationDate = user.getRegistrationDate();
    }
}
