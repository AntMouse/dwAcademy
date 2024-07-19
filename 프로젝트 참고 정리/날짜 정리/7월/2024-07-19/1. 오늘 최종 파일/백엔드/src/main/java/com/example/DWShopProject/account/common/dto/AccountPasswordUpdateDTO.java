package com.example.DWShopProject.account.common.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountPasswordUpdateDTO {
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;

    // 새 비밀번호와 확인 비밀번호가 일치하는지 확인하는 메서드
    public boolean isNewPasswordMatching() {
        return newPassword != null && !newPassword.isEmpty() && newPassword.equals(confirmNewPassword);
    }
}
