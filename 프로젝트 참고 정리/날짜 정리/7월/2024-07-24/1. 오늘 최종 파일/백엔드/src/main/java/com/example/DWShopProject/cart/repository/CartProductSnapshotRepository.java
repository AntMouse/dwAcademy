package com.example.DWShopProject.cart.repository;

import com.example.DWShopProject.cart.entity.CartProductSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductSnapshotRepository extends JpaRepository<CartProductSnapshot, Long> {
}
