package com.example.DWShopProject.service;

import com.example.DWShopProject.dao.OrderDto;
import com.example.DWShopProject.dao.OrderItemDto;
import com.example.DWShopProject.dao.SaleDto;
import com.example.DWShopProject.entity.Order;
import com.example.DWShopProject.entity.Sale;
import com.example.DWShopProject.repository.OrderRepository;
import com.example.DWShopProject.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * 모든 주문 항목을 매출에 포함시키는 메서드입니다.
     * @return 생성된 매출 객체를 DTO 형태로 반환합니다.
     */
    public List<SaleDto> createSale() {
        List<Order> orders = orderRepository.findAll();
        List<Sale> sales = orders.stream()
                .flatMap(order -> order.getOrderItems().stream()
                        .map(orderItem -> new Sale(order.getCreateDate(), orderItem.getProduct().getId(), orderItem.getPrice(), orderItem.getQuantity()))
                )
                .collect(Collectors.toList());

        List<Sale> savedSales = saleRepository.saveAll(sales);
        return savedSales.stream().map(this::convertToSaleDto).collect(Collectors.toList());
    }

    /**
     * 주문을 기반으로 매출 정보를 업데이트하는 메서드입니다.
     * @param order 주문 객체
     */
    public void updateSalesWithOrder(Order order) {
        List<Sale> sales = order.getOrderItems().stream()
                .map(orderItem -> new Sale(order.getCreateDate(), orderItem.getProduct().getId(), orderItem.getPrice(), orderItem.getQuantity()))
                .collect(Collectors.toList());

        saleRepository.saveAll(sales);
    }

    /**
     * 특정 기간 동안의 매출 목록을 반환하는 메서드입니다.
     * @param startDate 조회할 시작 날짜
     * @param endDate 조회할 종료 날짜
     * @return 기간 내의 매출 목록을 DTO 형태로 반환합니다.
     */
    public List<SaleDto> getSalesByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        List<Sale> sales = saleRepository.findByCreateDateBetween(startDate, endDate);
        return sales.stream().map(this::convertToSaleDto).collect(Collectors.toList());
    }

    /**
     * 특정 기간 동안의 총 매출 금액을 반환하는 메서드입니다.
     * @param startDate 조회할 시작 날짜
     * @param endDate 조회할 종료 날짜
     * @return 기간 내의 총 매출 금액
     */
    public int getTotalSalesAmountByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        List<Sale> sales = saleRepository.findByCreateDateBetween(startDate, endDate);
        return sales.stream().mapToInt(sale -> sale.getPrice() * sale.getQuantity()).sum();
    }

    /**
     * 오늘의 총 매출 금액을 반환하는 메서드입니다.
     * @return 오늘의 총 매출 금액
     */
    public List<SaleDto> getTodaySales() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return getSalesByPeriod(startOfDay, endOfDay);
    }

    /**
     * 이번 분기의 총 매출 금액을 반환하는 메서드입니다.
     * @return 이번 분기의 총 매출 금액
     */
    public int getQuarterlySalesAmount() {
        LocalDateTime startOfQuarter = getStartOfQuarter(LocalDate.now());
        LocalDateTime endOfQuarter = getEndOfQuarter(LocalDate.now());
        return getTotalSalesAmountByPeriod(startOfQuarter, endOfQuarter);
    }

    /**
     * 이번 달의 총 매출 금액을 반환하는 메서드입니다.
     * @return 이번 달의 총 매출 금액
     */
    public int getMonthlySalesAmount() {
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atTime(LocalTime.MAX);
        return getTotalSalesAmountByPeriod(startOfMonth, endOfMonth);
    }

    /**
     * 올해의 총 매출 금액을 반환하는 메서드입니다.
     * @return 올해의 총 매출 금액
     */
    public int getYearlySalesAmount() {
        LocalDateTime startOfYear = LocalDate.now().withDayOfYear(1).atStartOfDay();
        LocalDateTime endOfYear = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear()).atTime(LocalTime.MAX);
        return getTotalSalesAmountByPeriod(startOfYear, endOfYear);
    }

    /**
     * 주어진 날짜를 기준으로 해당 분기의 시작 날짜를 반환하는 메서드입니다.
     * @param date 기준 날짜
     * @return 해당 분기의 시작 날짜
     */
    public LocalDateTime getStartOfQuarter(LocalDate date) {
        int currentQuarter = (date.getMonthValue() - 1) / 3 + 1;
        int startMonth = (currentQuarter - 1) * 3 + 1;
        return LocalDate.of(date.getYear(), startMonth, 1).atStartOfDay();
    }

    /**
     * 주어진 날짜를 기준으로 해당 분기의 종료 날짜를 반환하는 메서드입니다.
     * @param date 기준 날짜
     * @return 해당 분기의 종료 날짜
     */
    public LocalDateTime getEndOfQuarter(LocalDate date) {
        int currentQuarter = (date.getMonthValue() - 1) / 3 + 1;
        int endMonth = currentQuarter * 3;
        return LocalDate.of(date.getYear(), endMonth, date.withMonth(endMonth).lengthOfMonth()).atTime(LocalTime.MAX);
    }

    /**
     * 매출(Sale) 객체를 매출 DTO(SaleDto)로 변환하는 메서드입니다.
     * @param sale 매출 객체
     * @return 매출 DTO
     */
    private SaleDto convertToSaleDto(Sale sale) {
        return SaleDto.builder()
                .id(sale.getId())
                .createDate(sale.getCreateDate())
                .productId(sale.getProductId())
                .price(sale.getPrice())
                .quantity(sale.getQuantity())
                .build();
    }
}
