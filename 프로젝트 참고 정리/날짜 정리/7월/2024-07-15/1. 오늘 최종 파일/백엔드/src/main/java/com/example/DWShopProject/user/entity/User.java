package com.example.DWShopProject.user.entity;

import com.example.DWShopProject.account.entity.BaseAccount;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.order.entity.Order;
import com.example.DWShopProject.security.enums.ApplicationRole;
import com.example.DWShopProject.user.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements BaseAccount {
    // 전 계정 공용 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountId;

    @JsonIgnore
    private String password;

    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplicationRole accountRole;

    private LocalDateTime registrationDate;

    // User 계정 전용 필드
    @Column(nullable = false)
    private String username;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String birthdate;
    private String contact;
    private boolean isActive;
    private boolean isMarketingOptIn;

    // 외래키
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // 빌더&메서드
    @Builder
    public User(String accountId, String password, String email, ApplicationRole accountRole, String username,
                Gender gender, String birthdate, String contact, boolean isMarketingOptIn, Address address) {
        this.accountId = accountId;
        this.password = password;
        this.email = email;
        this.accountRole = accountRole;
        this.registrationDate = LocalDateTime.now(); // 기본값 설정이 있어서 서비스에서 따로 처리 안 함

        this.username = username;
        this.gender = gender;
        this.birthdate = birthdate;
        this.contact = contact;
        this.isActive = true; // 기본값 설정이 있어서 서비스에서 따로 처리 안 함
        this.isMarketingOptIn = isMarketingOptIn;
        this.address = address;
    }

    public void updateUserProfile(String email, String username, Gender gender, String birthdate, String contact) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        if (username != null && !username.isEmpty()) {
            this.username = username;
        }
        if (gender != null) {
            this.gender = gender;
        }
        if (birthdate != null && !birthdate.isEmpty()) {
            this.birthdate = birthdate;
        }
        if (contact != null && !contact.isEmpty()) {
            this.contact = contact;
        }
    }

    public void updateUserStatus(boolean isActive, boolean isMarketingOptIn) {
        if (this.isActive != isActive) {
            this.isActive = isActive;
        }
        if (this.isMarketingOptIn != isMarketingOptIn) {
            this.isMarketingOptIn = isMarketingOptIn;
        }
    }

    @Override
    public void updatePassword(String password) {
        if (password != null) {
            this.password = password;
        }
    }

    @Override
    public void updateAccountRole(ApplicationRole accountRole) {
        if (accountRole != null) {
            this.accountRole = accountRole;
        }
    }
}
