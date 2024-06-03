package com.example.DWShopProject.security;

import com.example.DWShopProject.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 사용자 세부 정보를 구현한 클래스
public class MemberDetailsImpl implements UserDetails {
    private final Member member; // 멤버 객체
    private final String password; // 비밀번호
    private final String userId; // 사용자 ID

    // 생성자
    public MemberDetailsImpl(Member member, String password, String userId) {
        this.member = member;
        this.password = password;
        this.userId = userId;
    }

    // 멤버 객체 반환
    public Member getMember() {
        return this.member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한을 반환
        MemberRoleEnum userType = member.getMemberType();
        String authority = userType.getAuthority();

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
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정이 만료되지 않았음을 나타냄
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정이 잠기지 않았음을 나타냄
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 증명이 만료되지 않았음을 나타냄
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정이 활성화되었음을 나타냄
    }
}
