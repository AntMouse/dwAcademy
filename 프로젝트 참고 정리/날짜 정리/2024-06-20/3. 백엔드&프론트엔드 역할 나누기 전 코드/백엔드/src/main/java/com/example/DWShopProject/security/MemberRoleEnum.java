package com.example.DWShopProject.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

// 사용자 역할을 정의한 열거형
public enum MemberRoleEnum {

    ROLE_USER(Authority.USER),
    ROLE_ADMIN(Authority.ADMIN);

    private final String authority;

    MemberRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

    @JsonCreator
    public static MemberRoleEnum fromString(String memberType) {
        for (MemberRoleEnum role : MemberRoleEnum.values()) {
            if (role.name().equalsIgnoreCase(memberType)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + memberType);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}