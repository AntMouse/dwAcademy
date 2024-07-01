package com.example.DWTransferScoutProject.admin.entity;

import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String adminId; // 기존 userId에서 변경

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private ApplicationRoleEnum userType;
}
