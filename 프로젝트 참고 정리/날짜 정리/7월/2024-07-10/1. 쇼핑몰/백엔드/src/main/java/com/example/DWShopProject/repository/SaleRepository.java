package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {
    List<Sale> findByCreateDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Sale> findByCreateDateBetweenAndProductIdIn(LocalDateTime startDate, LocalDateTime endDate, List<Long> productIds);
}
