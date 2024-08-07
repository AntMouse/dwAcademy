package com.example.DWShopProject.order.repository;

import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    Optional<Order> findByIdAndUser(Long id, User user);
}
