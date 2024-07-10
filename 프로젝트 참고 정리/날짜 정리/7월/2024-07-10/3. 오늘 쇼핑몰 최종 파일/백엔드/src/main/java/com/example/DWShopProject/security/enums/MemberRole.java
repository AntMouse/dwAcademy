package com.example.DWShopProject.security.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MemberRole {

    USER(Authority.USER),
    ADMIN(Authority.ADMIN);


    private final String authority;

    MemberRole(String authority) {
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
    public static MemberRole fromString(String memberType) {
        for (MemberRole role : MemberRole.values()) {
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
