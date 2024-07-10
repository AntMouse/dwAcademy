package com.example.DWShopProject.cart.repository;

import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByMember(Member member);
    Optional<Cart> findByMemberId(Long memberId);
}
