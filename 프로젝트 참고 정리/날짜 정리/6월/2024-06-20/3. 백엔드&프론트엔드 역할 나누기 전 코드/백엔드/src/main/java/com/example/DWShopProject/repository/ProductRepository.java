package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.enums.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByProductName(String productName); // 상품명을 통해 단일 상품 검색

    List<Product> findByProductNameContainingIgnoreCase(String productName); // 상품명을 통해 상품 목록 검색 (대소문자 구분 없음)
    List<Product> findByProductType(ProductTypeEnum productType); // 상품 유형을 통해 상품 목록 검색
}