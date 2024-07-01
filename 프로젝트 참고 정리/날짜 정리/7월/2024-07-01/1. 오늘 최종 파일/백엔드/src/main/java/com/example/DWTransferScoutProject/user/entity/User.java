package com.example.DWTransferScoutProject.user.entity;

import com.example.DWTransferScoutProject.auth.security.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userType;

    @Column(nullable = false, unique = true)
    private String userId; // 사이트에서 사용하는 ID

    @Column(nullable = false)
    private String username; // 회원의 본명

    @JsonIgnore
    private String password;

    private String birthdate;
    private String gender;
    private String email;
    private String contact;

    @Builder
    public User(UserRoleEnum userType, String userId, String username, String password,
                String birthdate, String gender, String email, String contact) {
        this.userType = userType;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.contact = contact;
    }

    public void updateUserInfo(String username, String password, String birthdate, String gender, String email, String contact) {
        if (username != null) this.username = username;
        if (password != null) this.password = password;
        if (birthdate != null) this.birthdate = birthdate;
        if (gender != null) this.gender = gender;
        if (email != null) this.email = email;
        if (contact != null) this.contact = contact;
    }

    public void updateUserType(UserRoleEnum userType) {
        if (userType != null) {
            this.userType = userType;
        }
    }
}
