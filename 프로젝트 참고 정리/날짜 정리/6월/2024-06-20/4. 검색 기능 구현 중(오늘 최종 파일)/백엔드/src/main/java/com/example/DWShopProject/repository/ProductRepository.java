// src/main/java/com/example/DWShopProject/repository/ProductRepository.java
package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.enums.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByProductName(String productName); // 상품명을 통해 단일 상품 검색

    // 상품명으로 검색
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword%")
    List<Product> searchByNameContaining(@Param("keyword") String keyword);

    // 상품명 또는 설명으로 검색
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% OR p.explanation LIKE %:keyword%")
    List<Product> searchByNameOrDescriptionContaining(@Param("keyword") String keyword);

    // 카테고리로 검색
    @Query("SELECT p FROM Product p WHERE p.productType IN :productTypes")
    List<Product> searchByProductTypes(@Param("productTypes") List<ProductTypeEnum> productTypes);
}
