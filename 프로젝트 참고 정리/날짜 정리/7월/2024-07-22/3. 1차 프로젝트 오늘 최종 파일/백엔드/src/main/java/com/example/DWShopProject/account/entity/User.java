package com.example.DWShopProject.account.entity;

import com.example.DWShopProject.account.common.entity.Account;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.order.entity.Order;
import com.example.DWShopProject.common.security.enums.ApplicationRole;
import com.example.DWShopProject.account.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements Account {
    // 전 계정 공용 필드. 단, email은 user와 admin이 설정이 다르다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountId;

    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDateTime registrationDate;

    // User 계정 전용 필드
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplicationRole accountRole;

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

    // 생성자&메서드
    public User(String accountId, String password, String email, ApplicationRole accountRole, String username,
                Gender gender, String birthdate, String contact, boolean isMarketingOptIn, Address address) {
        this.accountId = accountId;
        this.password = password;
        this.email = email;
        this.registrationDate = LocalDateTime.now(); // 기본값 설정이 있어서 서비스에서 따로 처리 안 함

        this.accountRole = accountRole;
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
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
    }
}
