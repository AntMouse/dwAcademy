package com.example.DWShopProject.service;

import com.example.DWShopProject.dao.SaleDto;
import com.example.DWShopProject.entity.Order;
import com.example.DWShopProject.entity.OrderItem;
import com.example.DWShopProject.entity.Sale;
import com.example.DWShopProject.repository.OrderRepository;
import com.example.DWShopProject.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private OrderRepository orderRepository;

    // 1. 매출 초기화
    /**
     * 모든 주문 항목을 매출에 포함시키는 메서드입니다.
     * 매출 레퍼지토리를 초기화하고 주문 데이터를 기반으로 매출 정보를 다시 생성합니다.
     * @return 생성된 매출 객체를 DTO 형태로 반환합니다.
     */
    public List<SaleDto> createSale() {
        // 현재 존재하는 매출 데이터를 조회합니다.
        List<Sale> existingSales = saleRepository.findAll();

        // 모든 주문 데이터를 가져옵니다.
        List<Order> orders = orderRepository.findAll();

        // 주문 데이터를 기반으로 현재 매출 레퍼지토리에 없는 주문 항목들을 삭제합니다.
        List<Sale> salesToDelete = existingSales.stream()
                .filter(sale -> !isOrderExistInSales(orders, sale))
                .collect(Collectors.toList());

        saleRepository.deleteAll(salesToDelete);

        // 새로운 매출 정보를 저장하기 위한 리스트를 생성합니다.
        List<Sale> newSales = orders.stream()
                .flatMap(order -> {
                    List<OrderItem> orderItems = order.getOrderItems();
                    return orderItems.stream()
                            .filter(orderItem -> !isSaleExist(existingSales, order, orderItem)) // 기존 매출 데이터에 없는 주문 항목만 필터링합니다.
                            .map(orderItem -> new Sale(order.getCreateDate(), orderItem.getProduct().getId(), orderItem.getPrice(), orderItem.getQuantity()));
                })
                .collect(Collectors.toList());

        // 새로운 매출 정보를 저장합니다.
        List<Sale> savedSales = saleRepository.saveAll(newSales);
        return savedSales.stream().map(this::convertToSaleDto).collect(Collectors.toList());
    }

    /**
     * 주어진 주문과 주문 항목이 기존 매출 데이터에 존재하는지 확인하는 메서드입니다.
     * @param existingSales 기존 매출 데이터 리스트
     * @param order 주문 객체
     * @param orderItem 주문 항목 객체
     * @return 기존 매출 데이터에 존재하면 true, 그렇지 않으면 false
     */
    private boolean isSaleExist(List<Sale> existingSales, Order order, OrderItem orderItem) {
        return existingSales.stream().anyMatch(sale ->
                sale.getCreateDate().equals(order.getCreateDate())
                        && sale.getProductId().equals(orderItem.getProduct().getId())
                        && sale.getPrice() == orderItem.getPrice()
                        && sale.getQuantity() == orderItem.getQuantity()  // 수량을 고려합니다.
        );
    }

    /**
     * 주어진 매출 항목이 주문 데이터에 존재하는지 확인하는 메서드입니다.
     * @param orders 주문 데이터 리스트
     * @param sale 매출 항목 객체
     * @return 주문 데이터에 존재하면 true, 그렇지 않으면 false
     */
    private boolean isOrderExistInSales(List<Order> orders, Sale sale) {
        return orders.stream().anyMatch(order ->
                order.getOrderItems().stream().anyMatch(orderItem ->
                        sale.getCreateDate().equals(order.getCreateDate()) &&
                                sale.getProductId().equals(orderItem.getProduct().getId()) &&
                                sale.getPrice() == orderItem.getPrice() &&
                                sale.getQuantity() == orderItem.getQuantity()  // 수량을 고려합니다.
                )
        );
    }
    // 1. 매출 초기화 끝
    
    // 2. 매출 정보 반환
    /**
     * 특정 기간 동안의 매출 목록을 반환하는 메서드입니다.
     * @param startDate 조회할 시작 날짜
     * @param endDate 조회할 종료 날짜
     * @return 기간 내의 매출 목록을 DTO 형태로 반환합니다.
     */
    public List<SaleDto> getSalesByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        // 매출 데이터를 최신 상태로 업데이트합니다.
        createSale();

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
        // 매출 데이터를 최신 상태로 업데이트합니다.
        createSale();

        List<Sale> sales = saleRepository.findByCreateDateBetween(startDate, endDate);
        return sales.stream().mapToInt(sale -> sale.getPrice() * sale.getQuantity()).sum();
    }
    // 2. 매출 정보 반환 끝

    // 3. 특정 기간 매출 정보 반환
    // 3-1. 오늘 매출 반환
    /**
     * 오늘의 총 매출 금액을 반환하는 메서드입니다.
     * @return 오늘의 총 매출 금액
     */
    public List<SaleDto> getTodaySales() {
        // 매출 데이터를 최신 상태로 업데이트합니다.
        createSale();

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return getSalesByPeriod(startOfDay, endOfDay);
    }

    // 3-2. 각 월의 매출 반환
    /**
     * 특정 년도의 특정 월의 매출 목록을 반환하는 메서드입니다.
     * @param year 조회할 년도
     * @param month 조회할 월
     * @return 해당 월의 매출 목록을 DTO 형태로 반환합니다.
     */
    public List<SaleDto> getSalesByMonth(int year, int month) {
        LocalDateTime startOfMonth = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.toLocalDate().lengthOfMonth()).with(LocalTime.MAX);
        List<Sale> sales = saleRepository.findByCreateDateBetween(startOfMonth, endOfMonth);
        return sales.stream().map(this::convertToSaleDto).collect(Collectors.toList());
    }

    /**
     * 특정 년도의 특정 월의 총 매출 금액을 반환하는 메서드입니다.
     * @param year 조회할 년도
     * @param month 조회할 월
     * @return 해당 월의 총 매출 금액
     */
    public int getTotalSalesAmountByMonth(int year, int month) {
        LocalDateTime startOfMonth = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.toLocalDate().lengthOfMonth()).with(LocalTime.MAX);
        List<Sale> sales = saleRepository.findByCreateDateBetween(startOfMonth, endOfMonth);
        return sales.stream().mapToInt(sale -> sale.getPrice() * sale.getQuantity()).sum();
    }

    // 3-3. 각 분기 매출 반환
    /**
     * 주어진 분기의 시작 날짜와 시간을 반환하는 메서드입니다.
     * @param quarter 조회할 분기 (1, 2, 3, 4)
     * @return 해당 분기의 시작 날짜와 시간
     */
    public LocalDateTime getStartOfQuarter(int quarter) {
        int startMonth = (quarter - 1) * 3 + 1; // 분기의 시작 월을 계산합니다. 예를 들어, 1분기는 1월, 2분기는 4월 등.
        return LocalDate.of(LocalDate.now().getYear(), startMonth, 1).atStartOfDay(); // 현재 연도의 시작 월의 첫 번째 날의 자정 시간을 반환합니다.
    }

    /**
     * 주어진 분기의 종료 날짜와 시간을 반환하는 메서드입니다.
     * @param quarter 조회할 분기 (1, 2, 3, 4)
     * @return 해당 분기의 종료 날짜와 시간
     */
    public LocalDateTime getEndOfQuarter(int quarter) {
        int endMonth = quarter * 3; // 분기의 종료 월을 계산합니다. 예를 들어, 1분기는 3월, 2분기는 6월 등.
        return LocalDate.of(LocalDate.now().getYear(), endMonth, LocalDate.of(LocalDate.now().getYear(), endMonth, 1).lengthOfMonth()).atTime(LocalTime.MAX); // 현재 연도의 종료 월의 마지막 날의 마지막 시간을 반환합니다.
    }

    /**
     * 특정 달의 주간 매출 목록을 반환하는 메서드입니다.
     * @param year 조회할 연도
     * @param month 조회할 월 (1 ~ 12)
     * @return 주별 매출 목록을 DTO 형태로 반환합니다.
     */
    public List<Map<String, Object>> getWeeklySales(int year, int month) {
        // 매출 데이터를 최신 상태로 업데이트합니다.
        createSale();

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());

        List<Sale> sales = saleRepository.findByCreateDateBetween(firstDayOfMonth.atStartOfDay(), lastDayOfMonth.atTime(LocalTime.MAX));

        Map<Integer, Integer> weeklySales = new HashMap<>();
        for (Sale sale : sales) {
            int weekOfMonth = sale.getCreateDate().get(ChronoField.ALIGNED_WEEK_OF_MONTH);
            weeklySales.put(weekOfMonth, weeklySales.getOrDefault(weekOfMonth, 0) + sale.getPrice() * sale.getQuantity());
        }

        List<Map<String, Object>> weeklySalesList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : weeklySales.entrySet()) {
            Map<String, Object> weekData = new HashMap<>();
            weekData.put("week", entry.getKey());
            weekData.put("totalSalesAmount", entry.getValue());
            weeklySalesList.add(weekData);
        }

        return weeklySalesList;
    }

    /**
     * 각 상품별 총 매출 금액을 반환하는 메서드입니다.
     * @return 상품 ID를 키로 하고, 해당 상품의 총 매출 금액을 값으로 가지는 Map 객체
     */
    // 각 상품별 매출 정보 얻기
    public Map<Long, Integer> getSalesByProduct() {
        // 매출 데이터를 최신 상태로 업데이트합니다.
        createSale();

        List<Sale> sales = saleRepository.findAll(); // 모든 매출 정보를 가져옵니다.
        return sales.stream().collect(Collectors.groupingBy(Sale::getProductId, Collectors.summingInt(sale -> sale.getPrice() * sale.getQuantity()))); // 각 상품별로 매출 금액을 합산하여 Map으로 반환합니다.
    }

    /**
     * 매출 목록과 총 매출 금액을 포함하는 응답 데이터를 생성하는 메서드입니다.
     * @param sales 매출 목록
     * @param totalSalesAmount 총 매출 금액
     * @return 매출 목록과 총 매출 금액을 포함하는 Map 객체
     */
    public Map<String, Object> getSalesResponse(List<SaleDto> sales, int totalSalesAmount) {
        Map<String, Object> response = new HashMap<>(); // 응답 데이터를 담을 Map 객체를 생성합니다.
        response.put("sales", sales); // 매출 목록을 응답 데이터에 추가합니다.
        response.put("totalSalesAmount", totalSalesAmount); // 총 매출 금액을 응답 데이터에 추가합니다.
        return response; // 응답 데이터를 반환합니다.
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
