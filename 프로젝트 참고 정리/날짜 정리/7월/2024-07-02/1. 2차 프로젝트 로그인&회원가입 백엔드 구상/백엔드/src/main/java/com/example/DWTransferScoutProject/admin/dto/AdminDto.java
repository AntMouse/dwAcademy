package com.example.DWTransferScoutProject.admin.dto;

import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private Long id;
    private String adminId;
    private String password;
    private String adminEmail;
    private ApplicationRoleEnum accountType;
}
