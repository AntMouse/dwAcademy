package com.example.DWShopProject.dto;

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
    private String memberType;
    private String memberName;
    private String memberId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String birthdate;
    private String gender;
    private String email;
    private String contact;
    private String address;
}
