package com.example.DWTransferScoutProject.auth.security;

import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.admin.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final User user;
    private final Admin admin;
    private final String password;
    private final String userId; // userId로 변경
    private final ApplicationRoleEnum role;

    public UserDetailsImpl(User user, Admin admin, String password, String userId, ApplicationRoleEnum role) {
        this.user = user;
        this.admin = admin;
        this.password = password;
        this.userId = userId;
        this.role = role;
    }

    public User getUser() {
        return this.user;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = role.getAuthority();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId; // userId로 변경
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
