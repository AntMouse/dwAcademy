package com.example.DWShopProject.dto;

import com.example.DWShopProject.security.MemberRoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private MemberRoleEnum memberType;
    private String memberId;
    private String memberName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String birthdate;
    private String gender;
    private String email;
    private String contact;
    private String address;
}
