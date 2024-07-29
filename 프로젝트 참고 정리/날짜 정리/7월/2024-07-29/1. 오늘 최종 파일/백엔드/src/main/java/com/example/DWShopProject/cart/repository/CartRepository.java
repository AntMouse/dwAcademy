package com.example.DWShopProject.cart.repository;

import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
    Optional<Cart> findByUserId(Long userId);
}
