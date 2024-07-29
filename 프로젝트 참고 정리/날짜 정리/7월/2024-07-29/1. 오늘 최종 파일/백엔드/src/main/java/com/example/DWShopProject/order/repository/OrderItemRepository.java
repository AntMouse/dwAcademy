package com.example.DWShopProject.order.repository;

import com.example.DWShopProject.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
