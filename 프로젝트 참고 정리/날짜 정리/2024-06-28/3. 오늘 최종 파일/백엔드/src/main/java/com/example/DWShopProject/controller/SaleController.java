package com.example.DWShopProject.controller;

import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    /**
     * 매출 데이터를 다양한 조건에 따라 조회합니다.
     * @param startDate 조회 시작 날짜와 시간
     * @param endDate 조회 종료 날짜와 시간
     * @param productIds 조회할 상품 ID 목록 (콤마로 구분)
     * @param productType 조회할 상품 타입
     * @return 매출 데이터와 총 매출 금액을 포함하는 Map 객체
     */
    @GetMapping
    public Map<String, Object> getSales(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            @RequestParam(required = false) List<Long> productIds,
            @RequestParam(required = false) ProductTypeEnum productType) {
        return saleService.getSales(startDate, endDate, productIds, productType);
    }
}
