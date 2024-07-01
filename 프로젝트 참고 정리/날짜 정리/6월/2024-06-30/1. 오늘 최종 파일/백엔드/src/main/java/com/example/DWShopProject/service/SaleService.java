package com.example.DWShopProject.service;

import com.example.DWShopProject.dao.SaleDto;
import com.example.DWShopProject.entity.Order;
import com.example.DWShopProject.entity.OrderItem;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.entity.Sale;
import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.OrderRepository;
import com.example.DWShopProject.repository.ProductRepository;
import com.example.DWShopProject.repository.ProductTypeMgmtRepository;
import com.example.DWShopProject.repository.SaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private ProductTypeMgmtRepository productTypeMgmtRepository;

    // 주문 데이터를 기반으로 매출 데이터를 생성하는 메서드
    public List<SaleDto> createSalesFromOrders() {
        // 기존 매출 데이터 조회
        List<Sale> existingSales = saleRepository.findAll();
        // 모든 주문 데이터 조회
        List<Order> orders = orderRepository.findAll();
        // 존재하지 않는 주문과 관련된 매출 데이터 필터링
        List<Sale> salesToDelete = existingSales.stream()
                .filter(sale -> !isOrderExistInSales(orders, sale))
                .collect(Collectors.toList());
        // 필터링된 매출 데이터 삭제
        saleRepository.deleteAll(salesToDelete);
        // 새로운 매출 데이터 생성
        List<Sale> newSales = orders.stream()
                .flatMap(order -> order.getOrderItems().stream()
                        .filter(orderItem -> !isSaleExist(existingSales, order, orderItem))
                        .map(orderItem -> {
                            Product product = orderItem.getProduct();
                            return new Sale(order.getCreateDate(), product.getId(), orderItem.getPrice(), orderItem.getQuantity());
                        }))
                .collect(Collectors.toList());
        // 새로운 매출 데이터 저장
        List<Sale> savedSales = saleRepository.saveAll(newSales);
        // 매출 데이터를 DTO로 변환하여 반환
        return savedSales.stream().map(this::convertToSaleDto).collect(Collectors.toList());
    }


    // 매출 데이터가 존재하는지 확인하는 메서드
    private boolean isSaleExist(List<Sale> existingSales, Order order, OrderItem orderItem) {
        return existingSales.stream().anyMatch(sale ->
                sale.getCreateDate().equals(order.getCreateDate())
                        && sale.getProductId().equals(orderItem.getProduct().getId())
                        && sale.getPrice() == orderItem.getPrice()
                        && sale.getQuantity() == orderItem.getQuantity());
    }

    // 주문이 매출 데이터에 존재하는지 확인하는 메서드
    private boolean isOrderExistInSales(List<Order> orders, Sale sale) {
        return orders.stream().anyMatch(order ->
                order.getOrderItems().stream().anyMatch(orderItem ->
                        sale.getCreateDate().equals(order.getCreateDate())
                                && sale.getProductId().equals(orderItem.getProduct().getId())
                                && sale.getPrice() == orderItem.getPrice()
                                && sale.getQuantity() == orderItem.getQuantity()));
    }

    // 주어진 조건에 따라 판매 데이터를 조회하고, 이를 정렬하여 응답 데이터를 생성합니다.
    public Map<String, Object> getSales(LocalDateTime startDate, LocalDateTime endDate, List<Long> productIds, List<ProductTypeEnum> productSubTypes, List<ParentTypeEnum> productMainTypes, String sortType) {
        // 주문 데이터를 기반으로 매출 데이터 갱신
        createSalesFromOrders();
        // 특정 조건에 맞는 매출 데이터 조회
        List<Sale> sales = fetchSalesByCriteria(startDate, endDate, productIds, productSubTypes, productMainTypes);
        // 총 매출 금액 계산
        int totalSalesAmount = sales.stream().mapToInt(sale -> sale.getPrice() * sale.getQuantity()).sum();
        // 매출 데이터를 DTO로 변환
        List<SaleDto> saleDtos = sales.stream().map(this::convertToSaleDto).collect(Collectors.toList());
        // 매출 데이터 정렬
        saleDtos = sortSales(saleDtos, sortType);
        // 응답 데이터 생성
        return getSalesResponse(saleDtos, totalSalesAmount);
    }

    // 다양한 조건에 따라 판매 데이터를 필터링하여 조회합니다.
    private List<Sale> fetchSalesByCriteria(LocalDateTime startDate, LocalDateTime endDate, List<Long> productIds, List<ProductTypeEnum> productSubTypes, List<ParentTypeEnum> productMainTypes) {
        // 기본 조건으로 기간 내의 모든 매출 데이터 조회
        List<Sale> sales = saleRepository.findByCreateDateBetween(startDate, endDate);
        // 추가 조건에 따라 매출 데이터 필터링
        if (productIds != null && !productIds.isEmpty()) {
            sales = filterByProductIds(sales, productIds);
        }
        if (productSubTypes != null && !productSubTypes.isEmpty()) {
            sales = filterBySubTypes(sales, productSubTypes);
        }
        if (productMainTypes != null && !productMainTypes.isEmpty()) {
            sales = filterByMainTypes(sales, productMainTypes);
        }
        return sales;
    }

    // 주어진 상품 ID 목록에 해당하는 판매 데이터를 필터링합니다.
    private List<Sale> filterByProductIds(List<Sale> sales, List<Long> productIds) {
        return sales.stream()
                .filter(sale -> productIds.contains(sale.getProductId()))
                .collect(Collectors.toList());
    }

    // 주어진 서브 타입 목록에 해당하는 판매 데이터를 필터링합니다.
    private List<Sale> filterBySubTypes(List<Sale> sales, List<ProductTypeEnum> subTypes) {
        return sales.stream()
                .filter(sale -> {
                    Product product = productRepository.findById(sale.getProductId()).orElse(null);
                    return product != null && subTypes.contains(product.getProductType());
                })
                .collect(Collectors.toList());
    }

    // 주어진 메인 타입 목록에 해당하는 판매 데이터를 필터링합니다.
    private List<Sale> filterByMainTypes(List<Sale> sales, List<ParentTypeEnum> mainTypes) {
        return sales.stream()
                .filter(sale -> {
                    Product product = productRepository.findById(sale.getProductId()).orElse(null);
                    return product != null && mainTypes.contains(product.getProductType().getParentTypeEnum());
                })
                .collect(Collectors.toList());
    }


    // 주어진 메인 타입 목록에 해당하는 모든 서브 타입의 상품 ID를 조회합니다.
    private List<Long> getProductIdsByMainTypes(List<ParentTypeEnum> productMainTypes) {
        return productRepository.findByProductTypeIn(
                        productMainTypes.stream()
                                .flatMap(mainType -> Arrays.stream(ProductTypeEnum.values())
                                        .filter(subType -> subType.getParentTypeEnum().equals(mainType)))
                                .collect(Collectors.toList())
                ).stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    // 주어진 서브 타입 목록에 해당하는 상품 ID를 조회합니다.
    private List<Long> getProductIdsBySubTypes(List<ProductTypeEnum> productSubTypes) {
        return productRepository.findByProductTypeIn(productSubTypes)
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    // 응답 데이터를 생성합니다. 조회된 매출 데이터와 총 매출 금액을 포함합니다.
    private Map<String, Object> getSalesResponse(List<SaleDto> sales, int totalSalesAmount) {
        Map<String, Object> response = new HashMap<>();
        // 매출 데이터와 총 매출 금액을 응답에 포함
        response.put("sales", sales); // 매출 데이터
        response.put("totalSalesAmount", totalSalesAmount); // 총 매출 금액

        // 매출이 없거나 0일 경우, 0원으로 표시
        if (sales.isEmpty()) {
            Map<String, Object> emptySale = new HashMap<>();
            emptySale.put("productId", null);
            emptySale.put("price", 0);
            emptySale.put("quantity", 0);
            emptySale.put("totalAmount", 0);
            response.put("sales", Collections.singletonList(emptySale));
        }

        return response;
    }

    // 조회된 매출 데이터를 주어진 정렬 기준에 따라 정렬합니다.
    private List<SaleDto> sortSales(List<SaleDto> sales, String sortType) {
        if (sortType == null) {
            return sales;
        }
        switch (sortType) {
            case "priceDesc":
                sales.sort(Comparator.comparingInt(SaleDto::getPrice).reversed());
                break;
            case "priceAsc":
                sales.sort(Comparator.comparingInt(SaleDto::getPrice));
                break;
            case "nameAsc":
                sales.sort(Comparator.comparing(sale -> productRepository.findById(sale.getProductId()).get().getProductName()));
                break;
            case "typeAsc":
                sales.sort(Comparator.comparing(sale -> sale.getProductSubType().getDisplayName()));
                break;
            case "typeDesc":
                sales.sort(Comparator.comparing((SaleDto sale) -> sale.getProductSubType().getDisplayName()).reversed());
                break;
            case "mainTypeAsc":
                sales.sort(Comparator.comparing(sale -> sale.getProductMainType().getDisplayName()));
                break;
            case "mainTypeDesc":
                sales.sort(Comparator.comparing((SaleDto sale) -> sale.getProductMainType().getDisplayName()).reversed());
                break;
        }
        return sales;
    }

    // 메인 타입의 displayName을 반환하는 메서드. productTypeMgmtRepository를 사용하여 메인 타입 정보를 조회하고, 해당 메인 타입의 displayName을 반환합니다.
    private String getMainTypeDisplayName(ProductTypeEnum productType) {
        Optional<ProductTypeMgmt> optionalProductTypeMgmt = productTypeMgmtRepository.findByProductType(productType);
        if (optionalProductTypeMgmt.isPresent()) {
            ProductTypeMgmt productTypeMgmt = optionalProductTypeMgmt.get();
            return productTypeMgmt.getParentType().getDisplayName();
        }
        return "";
    }

    // 주어진 상품 타입에 대한 메인 타입을 반환합니다.
    private ParentTypeEnum getMainType(ProductTypeEnum productType) {
        return productType.getParentTypeEnum();
    }

    // 매출 데이터를 DTO로 변환하는 메서드. Sale 엔티티를 SaleDto로 변환합니다. ProductRepository를 사용하여 제품 정보를 조회하고, 이를 바탕으로 SaleDto 객체를 생성합니다.
    private SaleDto convertToSaleDto(Sale sale) {
        Product product = productRepository.findById(sale.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        return SaleDto.builder()
                .id(sale.getId())
                .createDate(sale.getCreateDate())
                .productId(sale.getProductId())
                .price(sale.getPrice())
                .quantity(sale.getQuantity())
                .productSubType(product.getProductType()) // 서브 타입 정보 추가
                .productMainType(product.getProductType().getParentTypeEnum()) // 메인 타입 정보 추가
                .build();
    }
}
