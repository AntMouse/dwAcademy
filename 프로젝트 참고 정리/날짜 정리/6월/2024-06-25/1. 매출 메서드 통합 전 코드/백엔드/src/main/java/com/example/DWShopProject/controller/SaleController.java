package com.example.DWShopProject.controller;

import com.example.DWShopProject.dao.SaleDto;
import com.example.DWShopProject.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
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
        List<SaleDto> sales = saleService.getSalesByPeriod(startDate, endDate);
        int totalSalesAmount = saleService.getTotalSalesAmountByPeriod(startDate, endDate);
        return saleService.getSalesResponse(sales, totalSalesAmount);
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
        return saleService.getTotalSalesAmountByPeriod(startDate, endDate);
    }

    /**
     * 오늘의 매출 목록과 총 매출 금액을 반환합니다.
     *
     * @return 오늘의 매출 목록과 총 매출 금액
     */
    @GetMapping("/today")
    public Map<String, Object> getTodaySales() {
        List<SaleDto> sales = saleService.getTodaySales();
        int totalSalesAmount = sales.stream().mapToInt(sale -> sale.getPrice() * sale.getQuantity()).sum();
        return saleService.getSalesResponse(sales, totalSalesAmount);
    }

    @GetMapping("/quarter")
    public Map<String, Object> getQuarterlySales(@RequestParam int quarter) {
        List<SaleDto> sales = saleService.getSalesByPeriod(saleService.getStartOfQuarter(quarter), saleService.getEndOfQuarter(quarter));
        int totalSalesAmount = sales.stream().mapToInt(sale -> sale.getPrice() * sale.getQuantity()).sum();
        return saleService.getSalesResponse(sales, totalSalesAmount);
    }

    @GetMapping("/weekly")
    public List<Map<String, Object>> getWeeklySales(@RequestParam int year, @RequestParam int month) {
        return saleService.getWeeklySales(year, month);
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
        List<SaleDto> sales = saleService.getSalesByMonth(year, month);
        int totalSalesAmount = saleService.getTotalSalesAmountByMonth(year, month);
        return saleService.getSalesResponse(sales, totalSalesAmount);
    }

    /**
     * 올해의 매출 목록과 총 매출 금액을 반환합니다.
     *
     * @return 올해의 매출 목록과 총 매출 금액
     */
    @GetMapping("/year")
    public Map<String, Object> getYearlySales() {
        List<SaleDto> sales = saleService.getSalesByPeriod(LocalDateTime.now().withDayOfYear(1).toLocalDate().atStartOfDay(), LocalDateTime.now());
        int totalSalesAmount = sales.stream().mapToInt(sale -> sale.getPrice() * sale.getQuantity()).sum();
        return saleService.getSalesResponse(sales, totalSalesAmount);
    }



    @GetMapping("/product")
    public Map<Long, Integer> getSalesByProduct() {
        return saleService.getSalesByProduct();
    }
}
