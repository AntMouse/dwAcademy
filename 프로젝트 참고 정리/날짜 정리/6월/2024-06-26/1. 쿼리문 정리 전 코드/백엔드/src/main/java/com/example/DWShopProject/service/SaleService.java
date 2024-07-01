package com.example.DWShopProject.service;

import com.example.DWShopProject.dao.SaleDto;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.entity.Order;
import com.example.DWShopProject.entity.OrderItem;
import com.example.DWShopProject.entity.Sale;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.OrderRepository;
import com.example.DWShopProject.repository.ProductRepository;
import com.example.DWShopProject.repository.SaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // 1. 매출 초기화
    /**
     * 모든 주문 항목을 매출에 포함시키는 메서드입니다.
     * 매출 레퍼지토리를 초기화하고 주문 데이터를 기반으로 매출 정보를 다시 생성합니다.
     * @return 생성된 매출 객체를 DTO 형태로 반환합니다.
     */
    public List<SaleDto> createSale() {
        try {
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
            return savedSales.stream().map(this::convertToSaleDto).collect(Collectors.toList()); // 반환 타입을 DTO로 변환
        } catch (Exception e) {
            log.error("Error creating sales: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating sales", e);
        }
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
     * 매출 데이터를 최신 상태로 업데이트하고 특정 기간 동안의 매출 목록과 총 매출 금액을 반환하는 헬퍼 메서드입니다.
     * @param startDate 조회할 시작 날짜
     * @param endDate 조회할 종료 날짜
     * @return 매출 목록과 총 매출 금액을 포함하는 Map 객체
     */
    private Map<String, Object> getUpdatedSalesAndTotalAmountByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return getSalesDataByPeriod(startDate, endDate, true, true);
    }

    /**
     * 특정 기간 동안의 매출 목록 or 총 매출 금액을 반환하는 메서드입니다.
     * @param startDate 조회할 시작 날짜
     * @param endDate 조회할 종료 날짜
     * @param includeSales 매출 목록 포함 여부
     * @param includeTotalAmount 총 매출 금액 포함 여부
     * @return 기간 내의 매출 목록과/또는 총 매출 금액을 포함하는 Map 객체
     */
    public Map<String, Object> getSalesDataByPeriod(LocalDateTime startDate, LocalDateTime endDate, boolean includeSales, boolean includeTotalAmount) {
        try {
            // 매출 데이터를 최신 상태로 업데이트합니다.
            createSale();

            Map<String, Object> response = new HashMap<>();

            List<Sale> sales = saleRepository.findByCreateDateBetween(startDate, endDate);

            if (includeSales) {
                List<SaleDto> saleDtos = sales.stream().map(this::convertToSaleDto).collect(Collectors.toList()); // 반환 타입을 DTO로 변환
                response.put("sales", saleDtos);
            }

            if (includeTotalAmount) {
                int totalSalesAmount = sales.stream().mapToInt(sale -> sale.getPrice() * sale.getQuantity()).sum();
                response.put("totalSalesAmount", totalSalesAmount);
            }

            return response;
        } catch (Exception e) {
            log.error("Error retrieving sales data by period: {}", e.getMessage(), e);
            throw new RuntimeException("Error retrieving sales data by period", e);
        }
    }

    // 2. 매출 정보 반환 끝

    // 3. 특정 기간 매출 정보 반환
    // 각 기능별 메서드들을 getSalesDataByPeriod 메서드를 이용하여 구현

    // 3-1. 하루 매출
    /**
     * 오늘의 매출을 반환하는 메서드입니다.
     * @return 오늘의 매출 목록과 총 매출 금액을 포함하는 Map 객체
     */
    public Map<String, Object> getTodaySales() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return getSalesDataByPeriod(startOfDay, endOfDay, true, true);
    }

    // 3-2. 주 매출
    /**
     * 이번 달의 주차별 매출을 반환하는 메서드입니다.
     * @return 이번 달의 주차별 매출 리스트
     */
    public List<Map<String, Object>> getWeeklySalesThisMonth() {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        return getWeeklySales(currentYear, currentMonth);
    }

    /**
     * 특정 달의 주간 매출 목록과 총 매출 금액을 반환하는 메서드입니다.
     * @param year 조회할 연도
     * @param month 조회할 월 (1 ~ 12)
     * @return 주별 매출 목록과 총 매출 금액을 포함하는 Map 객체의 리스트
     */
    public List<Map<String, Object>> getWeeklySales(int year, int month) {
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

    // 3-3. 달 매출
    /**
     * 이번 달의 총 매출 금액을 반환하는 메서드입니다.
     * @return 이번 달의 총 매출 금액
     */
    public int getTotalSalesAmountThisMonth() {
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        return (int) getSalesDataByPeriod(startOfMonth, endOfMonth, false, true).get("totalSalesAmount");
    }

    /**
     * 특정 년도의 특정 월의 매출 목록과 총 매출 금액을 반환하는 메서드입니다.
     * @param year 조회할 년도
     * @param month 조회할 월
     * @return 해당 월의 매출 목록과 총 매출 금액을 포함하는 Map 객체
     */
    public Map<String, Object> getSalesAndTotalAmountByMonth(int year, int month) {
        LocalDateTime startOfMonth = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        return getSalesDataByPeriod(startOfMonth, endOfMonth, true, true);
    }

    /**
     * 특정 년도의 특정 분기의 매출 목록과 총 매출 금액을 반환하는 메서드입니다.
     * @param year 조회할 년도
     * @param quarter 조회할 분기 (1 ~ 4)
     * @return 해당 분기의 매출 목록과 총 매출 금액을 포함하는 Map 객체
     */
    public Map<String, Object> getSalesAndTotalAmountByQuarter(int year, int quarter) {
        LocalDateTime startOfQuarter;
        LocalDateTime endOfQuarter;

        switch (quarter) {
            case 1:
                startOfQuarter = LocalDate.of(year, 1, 1).atStartOfDay();
                endOfQuarter = LocalDate.of(year, 3, 31).atTime(LocalTime.MAX);
                break;
            case 2:
                startOfQuarter = LocalDate.of(year, 4, 1).atStartOfDay();
                endOfQuarter = LocalDate.of(year, 6, 30).atTime(LocalTime.MAX);
                break;
            case 3:
                startOfQuarter = LocalDate.of(year, 7, 1).atStartOfDay();
                endOfQuarter = LocalDate.of(year, 9, 30).atTime(LocalTime.MAX);
                break;
            case 4:
                startOfQuarter = LocalDate.of(year, 10, 1).atStartOfDay();
                endOfQuarter = LocalDate.of(year, 12, 31).atTime(LocalTime.MAX);
                break;
            default:
                throw new IllegalArgumentException("Invalid quarter: " + quarter);
        }

        return getSalesDataByPeriod(startOfQuarter, endOfQuarter, true, true);
    }

    // 3-4. 연도 매출
    /**
     * 특정 년도의 총 매출 목록과 총 매출 금액을 반환하는 메서드입니다.
     * @param year 조회할 년도
     * @return 해당 년도의 매출 목록과 총 매출 금액을 포함하는 Map 객체
     */
    public Map<String, Object> getSalesAndTotalAmountByYear(int year) {
        LocalDateTime startOfYear = LocalDate.of(year, 1, 1).atStartOfDay();
        LocalDateTime endOfYear = LocalDate.of(year, 12, 31).atTime(LocalTime.MAX);
        return getSalesDataByPeriod(startOfYear, endOfYear, true, true);
    }
    // 3. 특정 기간 매출 정보 반환 끝

    // 4. 상품별 매출 정보 반환
    /**
     * 각 상품별 총 매출 금액과 상품 이름을 페이징하여 반환하는 메서드입니다.
     * 매출 데이터를 최신 상태로 업데이트한 후, 각 상품의 총 매출 금액을 계산하고,
     * 해당 상품의 이름을 포함한 결과 리스트를 페이징하여 반환합니다.
     *
     * @param pageable 페이징 정보
     * @return 페이징된 상품 ID와 해당 상품의 총 매출 금액, 상품 이름을 포함하는 리스트
     */
    public Page<Map<String, Object>> getSalesByProductWithNames(Pageable pageable) {
        // 매출 데이터를 최신 상태로 업데이트합니다.
        createSale();

        // 모든 상품 정보를 미리 가져와서 맵으로 변환합니다.
        Map<Long, String> productNames = productRepository.findAll().stream()
                .collect(Collectors.toMap(Product::getId, Product::getProductName));

        // 각 상품의 총 매출 금액을 계산합니다.
        Map<Long, Integer> productSales = saleRepository.findAll().stream()
                .collect(Collectors.groupingBy(Sale::getProductId,
                        Collectors.summingInt(sale -> sale.getPrice() * sale.getQuantity())));

        // 모든 상품 정보를 포함한 결과 리스트를 생성합니다.
        List<Map<String, Object>> resultList = productNames.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> productSalesData = new HashMap<>();
                    productSalesData.put("productId", entry.getKey());
                    productSalesData.put("productName", entry.getValue());
                    productSalesData.put("totalSalesAmount", productSales.getOrDefault(entry.getKey(), 0));
                    return productSalesData;
                })
                .collect(Collectors.toList());

        // 페이징 처리
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), resultList.size());
        List<Map<String, Object>> pageContent = resultList.subList(start, end);
        return new PageImpl<>(pageContent, pageable, resultList.size());
    }

    /**
     * 각 상품 타입별 총 매출 금액을 반환하는 메서드입니다.
     * 매출 데이터를 최신 상태로 업데이트한 후, 각 상품 타입의 총 매출 금액을 계산하고,
     * 해당 타입의 이름과 총 매출 금액을 포함한 결과 리스트를 반환합니다.
     *
     * @return 상품 타입과 해당 타입의 총 매출 금액을 포함하는 리스트
     */
    public List<Map<String, Object>> getSalesByProductTypeWithNames() {
        // 매출 데이터를 최신 상태로 업데이트합니다.
        createSale();

        // 모든 상품 정보를 미리 가져와서 맵으로 변환합니다.
        Map<Long, Product> products = productRepository.findAll().stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        // 각 상품의 총 매출 금액을 계산합니다.
        Map<ProductTypeEnum, Integer> productTypeSales = saleRepository.findAll().stream()
                .collect(Collectors.groupingBy(sale -> products.get(sale.getProductId()).getProductType(),
                        Collectors.summingInt(sale -> sale.getPrice() * sale.getQuantity())));

        // 모든 상품 타입 정보를 포함한 결과 리스트를 생성합니다.
        return productTypeSales.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> productTypeSalesData = new HashMap<>();
                    productTypeSalesData.put("productType", entry.getKey().name());
                    productTypeSalesData.put("totalSalesAmount", entry.getValue());
                    return productTypeSalesData;
                })
                .collect(Collectors.toList());
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
