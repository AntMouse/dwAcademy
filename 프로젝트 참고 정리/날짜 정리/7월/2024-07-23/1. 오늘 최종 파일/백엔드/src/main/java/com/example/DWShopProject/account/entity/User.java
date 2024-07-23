package com.example.DWShopProject.account.entity;

import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.order.entity.Order;
import com.example.DWShopProject.common.security.enums.UserRole;
import com.example.DWShopProject.account.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    // 전 계정 공용 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDateTime registrationDate;

    @Column(nullable = false)
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<UserRole> roles;

    // 일반 회원 전용 필드
    private String username;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String birthdate;
    private String contact;
    private boolean isActive;
    private boolean isMarketingOptIn;

    // 일반 회원 전용 외래키
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // 생성자&메서드
    public User(String userId, String password, String email, Set<UserRole> roles, String username,
                Gender gender, String birthdate, String contact, boolean isMarketingOptIn, Address address) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registrationDate = LocalDateTime.now(); // 기본값 설정
        this.roles = roles;

        this.username = username;
        this.gender = gender;
        this.birthdate = birthdate;
        this.contact = contact;
        this.isActive = true; // 기본값 설정
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

    public void updateUserPassword(String password) {
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
    }

    public void updateUserRoles(Set<UserRole> roles) {
        if (roles != null && !roles.isEmpty()) {
            this.roles.addAll(roles);
        }
    }

    public void removeUserRoles(Set<UserRole> roles) {
        if (roles != null && !roles.isEmpty()) {
            this.roles.removeAll(roles);
        }
    }
}
