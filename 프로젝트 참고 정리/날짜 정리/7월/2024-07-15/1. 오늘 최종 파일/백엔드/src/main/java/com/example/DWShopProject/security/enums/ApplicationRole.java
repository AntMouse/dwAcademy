package com.example.DWShopProject.security.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationRole {

    USER(Authority.USER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    ApplicationRole(String authority) {
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
    public static ApplicationRole fromString(String userType) {
        for (ApplicationRole role : ApplicationRole.values()) {
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
