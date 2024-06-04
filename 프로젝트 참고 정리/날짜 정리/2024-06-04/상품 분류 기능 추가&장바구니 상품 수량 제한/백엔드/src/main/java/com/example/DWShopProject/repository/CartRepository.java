package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByMemberId(Long memberId); // memberId로 카트를 검색하는 메서드 추가 cart 엔티티에 memberId 있음
}
