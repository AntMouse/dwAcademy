package com.example.DWShopProject.entity;

import com.example.DWShopProject.security.MemberRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")  // 테이블명을 명시적으로 지정
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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
}
