package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByName(String name);  // Add this method
}
