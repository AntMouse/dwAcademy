package com.example.DWShopProject.controller;

import com.example.DWShopProject.dao.SaleDto;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    // 1. 기능
    /**
     * 모든 주문 항목을 포함하는 매출을 생성합니다.
     *
     * @return 생성된 매출 객체를 DTO 형태로 반환합니다.
     */
    @PostMapping("/create")
    public List<SaleDto> createSale() {
        return saleService.createSale();
    }

    /**
     * 특정 기간 동안의 매출 목록과 총 매출 금액을 반환합니다.
     * 쿼리 매개변수로 startDate와 endDate를 제공해야 합니다.
     * 예: /api/sales/period?startDate=2023-01-01T00:00:00&endDate=2023-12-31T23:59:59
     *
     * @param startDate 조회 시작 날짜와 시간
     * @param endDate 조회 종료 날짜와 시간
     * @return 기간 내의 매출 목록과 총 매출 금액
     */
    @GetMapping("/period")
    public Map<String, Object> getSalesByPeriod(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return saleService.getSalesDataByPeriod(startDate, endDate, true, true);
    }

    /**
     * 특정 기간 동안의 총 매출 금액을 반환합니다.
     * 쿼리 매개변수로 startDate와 endDate를 제공해야 합니다.
     * 예: /api/sales/total?startDate=2023-01-01T00:00:00&endDate=2023-12-31T23:59:59
     *
     * @param startDate 조회 시작 날짜와 시간
     * @param endDate 조회 종료 날짜와 시간
     * @return 기간 내의 총 매출 금액
     */
    @GetMapping("/total")
    public int getTotalSalesAmountByPeriod(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return (int) saleService.getSalesDataByPeriod(startDate, endDate, false, true).get("totalSalesAmount");
    }

    // 2. 오늘 매출
    /**
     * 오늘의 매출 목록과 총 매출 금액을 반환합니다.
     *
     * @return 오늘의 매출 목록과 총 매출 금액
     */
    @GetMapping("/today")
    public Map<String, Object> getTodaySales() {
        return saleService.getTodaySales();
    }

    // 3. 주간 매출
    /**
     * 이번 달의 주차별 매출 목록과 총 매출 금액을 반환합니다.
     *
     * @return 이번 달의 주차별 매출 목록과 총 매출 금액 리스트
     */
    @GetMapping("/weekly-this-month")
    public List<Map<String, Object>> getWeeklySalesThisMonth() {
        return saleService.getWeeklySalesThisMonth();
    }

    /**
     * 특정 달의 주간 매출 목록과 총 매출 금액을 반환합니다.
     * 쿼리 매개변수로 year와 month를 제공해야 합니다.
     * 예: /api/sales/weekly?year=2024&month=1
     *
     * @param year 조회할 년도
     * @param month 조회할 월
     * @return 해당 월의 주간 매출 목록과 총 매출 금액 리스트
     */
    @GetMapping("/weekly")
    public List<Map<String, Object>> getWeeklySales(@RequestParam int year, @RequestParam int month) {
        return saleService.getWeeklySales(year, month);
    }

    // 4. 월별 매출
    /**
     * 이번 달의 총 매출 금액을 반환합니다.
     *
     * @return 이번 달의 총 매출 금액
     */
    @GetMapping("/total-this-month")
    public int getTotalSalesAmountThisMonth() {
        return saleService.getTotalSalesAmountThisMonth();
    }

    /**
     * 특정 분기의 매출 목록과 총 매출 금액을 반환합니다.
     *
     * @param year 조회할 년도
     * @param quarter 조회할 분기
     * @return 해당 분기의 매출 목록과 총 매출 금액
     */
    @GetMapping("/quarter")
    public Map<String, Object> getQuarterlySales(@RequestParam int year, @RequestParam int quarter) {
        return saleService.getSalesAndTotalAmountByQuarter(year, quarter);
    }

    /**
     * 특정 년도의 특정 월의 매출 목록과 총 매출 금액을 반환합니다.
     * 쿼리 매개변수로 year와 month를 제공해야 합니다.
     * 예: /api/sales/month?year=2024&month=1
     *
     * @param year 조회할 년도
     * @param month 조회할 월
     * @return 해당 월의 매출 목록과 총 매출 금액
     */
    @GetMapping("/month")
    public Map<String, Object> getSalesByMonth(@RequestParam int year, @RequestParam int month) {
        return saleService.getSalesAndTotalAmountByMonth(year, month);
    }

    // 5. 연도 매출
    /**
     * 특정 년도의 매출 목록과 총 매출 금액을 반환합니다.
     * 쿼리 매개변수로 year를 제공해야 합니다.
     * 예: /api/sales/year?year=2024
     *
     * @param year 조회할 년도
     * @return 해당 년도의 매출 목록과 총 매출 금액
     */
    @GetMapping("/year")
    public Map<String, Object> getYearlySales(@RequestParam int year) {
        return saleService.getSalesAndTotalAmountByYear(year);
    }

    // 6. 상품 매출
    /**
     * 각 상품별 총 매출 금액과 상품 이름을 페이징하여 반환합니다.
     *
     * @param pageable 페이징 정보
     * @return 페이징된 상품 ID와 해당 상품의 총 매출 금액, 상품 이름을 포함하는 리스트
     */
    @GetMapping("/product")
    public Page<Map<String, Object>> getSalesByProductWithNames(Pageable pageable) {
        return saleService.getSalesByProductWithNames(pageable);
    }

    /**
     * 특정 메인 타입과 서브 타입에 대한 매출 금액을 반환합니다.
     *
     * @param parentType (옵션) 메인 타입 (예: 상의, 하의 등)
     * @param subType (옵션) 서브 타입 (예: 후드티셔츠, 반소매티셔츠 등)
     * @return 매출 데이터를 포함하는 리스트
     */
    @GetMapping("/product-type-sales")
    public List<Map<String, Object>> getSalesByProductTypeWithNames(
            @RequestParam(required = false) ParentTypeEnum parentType,
            @RequestParam(required = false) ProductTypeEnum subType) {
        // SaleService를 호출하여 매출 데이터를 가져옵니다.
        return saleService.getSalesByProductTypeWithNames(parentType, subType);
    }
}
