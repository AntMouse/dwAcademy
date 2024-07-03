package com.example.DWTransferScoutProject.auth.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationRoleEnum {

    USER(Authority.USER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    ApplicationRoleEnum(String authority) {
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
    public static ApplicationRoleEnum fromString(String userType) {
        for (ApplicationRoleEnum role : ApplicationRoleEnum.values()) {
            if (role.name().equalsIgnoreCase(userType)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + userType);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
