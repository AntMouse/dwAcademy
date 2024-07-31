package com.example.DWShopProject.user.util;

public class PasswordUtils {
    public static boolean isPasswordMatching(String password, String confirmPassword) {
        return password != null && !password.isEmpty() && password.equals(confirmPassword);
    }
}