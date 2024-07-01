package com.example.DWShopProject.entity;

import com.example.DWShopProject.security.MemberRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum memberType;

    private String memberId;
    private String memberName;
    private String password;
    private String birthdate;
    private String gender;
    private String email;
    private String contact;
    private String address;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @Builder
    public Member(MemberRoleEnum memberType, String memberId, String memberName, String password,
                  String birthdate, String gender, String email, String contact, String address) {
        this.memberType = memberType;
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

    public void updateMemberInfo(String memberName, String password, String birthdate, String gender, String email, String contact, String address) {
        if (memberName != null) this.memberName = memberName;
        if (password != null) this.password = password;
        if (birthdate != null) this.birthdate = birthdate;
        if (gender != null) this.gender = gender;
        if (email != null) this.email = email;
        if (contact != null) this.contact = contact;
        if (address != null) this.address = address;
    }

    // 회원 타입 업데이트 메서드 추가
    public void updateMemberType(MemberRoleEnum memberType) {
        if (memberType != null) {
            this.memberType = memberType;
        }
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setMember(this);
    }
}
