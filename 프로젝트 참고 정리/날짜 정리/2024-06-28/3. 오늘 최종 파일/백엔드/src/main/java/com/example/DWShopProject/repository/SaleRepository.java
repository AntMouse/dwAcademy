package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Sale;
import com.example.DWShopProject.enums.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    // createDate가 특정 기간 사이에 있는 Sale 목록을 조회하는 메서드
    List<Sale> findByCreateDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Sale> findByCreateDateBetweenAndProductIdIn(LocalDateTime startDate, LocalDateTime endDate, List<Long> productIds);
    List<Sale> findByCreateDateBetweenAndProductType(LocalDateTime startDate, LocalDateTime endDate, ProductTypeEnum productType);
}
