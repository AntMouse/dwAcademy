package com.example.DWShopProject.controller;

import com.example.DWShopProject.dao.SaleDto;
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

    /**
     * 오늘의 매출 목록과 총 매출 금액을 반환합니다.
     *
     * @return 오늘의 매출 목록과 총 매출 금액
     */
    @GetMapping("/today")
    public Map<String, Object> getTodaySales() {
        return saleService.getTodaySales();
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

    /**
     * 각 상품별 총 매출 금액을 반환합니다.
     *
     * @return 상품 ID를 키로 하고, 해당 상품의 총 매출 금액을 값으로 가지는 Map 객체
     */
    @GetMapping("/product")
    public Map<Long, Integer> getSalesByProduct() {
        return saleService.getSalesByProduct();
    }

    /**
     * 특정 상품 타입의 매출 목록과 총 매출 금액을 반환합니다.
     * 쿼리 매개변수로 productType을 제공해야 합니다.
     * 예: /api/sales/product-type?productType=HOODIES
     *
     * @param productType 조회할 상품 타입
     * @return 해당 상품 타입의 매출 목록과 총 매출 금액
     */
    @GetMapping("/product-type")
    public Map<String, Object> getSalesByProductType(@RequestParam ProductTypeEnum productType) {
        return saleService.getSalesByProductType(productType);
    }
}
