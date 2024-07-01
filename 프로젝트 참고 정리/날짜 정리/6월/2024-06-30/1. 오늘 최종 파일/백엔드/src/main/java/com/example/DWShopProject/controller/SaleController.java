package com.example.DWShopProject.controller;

import com.example.DWShopProject.dao.SalesRequestDto;
import com.example.DWShopProject.enums.ParentTypeEnum;
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

    // GET 요청으로 매출 데이터를 조회하는 엔드포인트
    // 클라이언트로부터 시작 날짜, 종료 날짜, 상품 ID 목록, 서브 타입 목록, 메인 타입 목록, 정렬 기준을 받아 매출 데이터를 조회합니다.
    // 조건이 많거나 데이터가 많은 경우, GET 요청이 아닌 POST 요청을 권장합니다.
    @GetMapping
    public Map<String, Object> getSales(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            @RequestParam(required = false) List<Long> productIds,
            @RequestParam(required = false) List<ProductTypeEnum> productSubTypes,
            @RequestParam(required = false) List<ParentTypeEnum> productMainTypes,
            @RequestParam(required = false) String sortType) {
        if ((productIds != null && productIds.size() > 30) ||
                ((productSubTypes != null && productSubTypes.size() > 30) || (productMainTypes != null && productMainTypes.size() > 30))) {
            throw new IllegalArgumentException("Too many product IDs or product types, use POST method.");
        }
        return saleService.getSales(startDate, endDate, productIds, productSubTypes, productMainTypes, sortType);
    }

    // POST 요청으로 매출 데이터를 조회하는 엔드포인트
    // 클라이언트로부터 SalesRequestDto 객체를 받아 매출 데이터를 조회합니다.
    // GET 요청보다 많은 데이터를 처리할 수 있으며, 조건이 많거나 데이터가 많은 경우에 사용합니다.
    @PostMapping
    public Map<String, Object> getSalesPost(
            @RequestBody SalesRequestDto salesRequest) {
        return saleService.getSales(salesRequest.getStartDate(), salesRequest.getEndDate(),
                salesRequest.getProductIds(), salesRequest.getProductSubTypes(), salesRequest.getProductMainTypes(),
                salesRequest.getSortType());
    }
}
