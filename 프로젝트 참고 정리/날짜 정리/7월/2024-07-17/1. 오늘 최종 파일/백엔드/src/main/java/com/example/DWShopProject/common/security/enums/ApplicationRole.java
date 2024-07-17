package com.example.DWShopProject.common.security.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationRole {
    USER(Authority.USER),
    SUPER_ADMIN(Authority.SUPER_ADMIN),
    ORDER_ADMIN(Authority.ORDER_ADMIN),
    PRODUCT_ADMIN(Authority.PRODUCT_ADMIN),
    REVIEW_ADMIN(Authority.REVIEW_ADMIN),
    CUSTOMER_SERVICE_ADMIN(Authority.CUSTOMER_SERVICE_ADMIN),
    MARKETING_ADMIN(Authority.MARKETING_ADMIN),
    SHIPPING_ADMIN(Authority.SHIPPING_ADMIN),
    CONTENT_ADMIN(Authority.CONTENT_ADMIN);

    private final String authority;

    ApplicationRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String SUPER_ADMIN = "ROLE_SUPER_ADMIN";
        public static final String ORDER_ADMIN = "ROLE_ORDER_ADMIN";
        public static final String PRODUCT_ADMIN = "ROLE_PRODUCT_ADMIN";
        public static final String REVIEW_ADMIN = "ROLE_REVIEW_ADMIN";
        public static final String CUSTOMER_SERVICE_ADMIN = "ROLE_CUSTOMER_SERVICE_ADMIN";
        public static final String MARKETING_ADMIN = "ROLE_MARKETING_ADMIN";
        public static final String SHIPPING_ADMIN = "ROLE_SHIPPING_ADMIN";
        public static final String CONTENT_ADMIN = "ROLE_CONTENT_ADMIN";
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
