package com.example.DWShopProject.security;

// 사용자 역할을 정의한 열거형
public enum MemberRoleEnum {

    USER(Authority.USER),
    ADMIN(Authority.ADMIN);

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

    public static MemberRoleEnum fromString(String memberType) {
        switch (memberType) {
            case "ROLE_USER":
                return USER;
            case "ROLE_ADMIN":
                return ADMIN;
            default:
                throw new IllegalArgumentException("Unknown role: " + memberType);
        }
    }
}