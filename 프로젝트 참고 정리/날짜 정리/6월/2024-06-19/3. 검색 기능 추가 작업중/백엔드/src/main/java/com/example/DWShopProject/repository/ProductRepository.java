package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.enums.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.createDate >= :startDate AND " +
            "(:keyword IS NULL OR " +
            "(:searchType = 'productName' AND p.productName LIKE %:keyword%) OR " +
            "(:searchType = 'productDescription' AND p.explanation LIKE %:keyword%) OR " +
            "(:searchType = 'productNameAndDescription' AND (p.productName LIKE %:keyword% OR p.explanation LIKE %:keyword%)) OR " +
            "(:searchType = 'category' AND p.productType = :categoryEnum))")
    List<Product> searchByDateAndConditions(@Param("startDate") LocalDateTime startDate,
                                            @Param("keyword") String keyword,
                                            @Param("searchType") String searchType,
                                            @Param("categoryEnum") ProductTypeEnum categoryEnum);
}
