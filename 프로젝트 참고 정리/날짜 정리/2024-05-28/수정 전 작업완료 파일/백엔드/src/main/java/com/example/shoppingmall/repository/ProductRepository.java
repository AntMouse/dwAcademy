package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);  // 상품 이름으로 검색하는 메서드 추가
}
