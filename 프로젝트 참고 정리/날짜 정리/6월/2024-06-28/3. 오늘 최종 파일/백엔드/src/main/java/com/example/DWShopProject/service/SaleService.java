package com.example.DWShopProject.service;

import com.example.DWShopProject.dao.SaleDto;
import com.example.DWShopProject.entity.Order;
import com.example.DWShopProject.entity.OrderItem;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.entity.Sale;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.OrderRepository;
import com.example.DWShopProject.repository.ProductRepository;
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

    public Map<String, Object> getSales(LocalDateTime startDate, LocalDateTime endDate, List<Long> productIds, ProductTypeEnum productType) {
        createSale();

        List<Sale> sales = fetchSales(startDate, endDate, productIds, productType);
        int totalSalesAmount = sales.stream().mapToInt(sale -> sale.getPrice() * sale.getQuantity()).sum();

        List<SaleDto> saleDtos = sales.stream().map(this::convertToSaleDto).collect(Collectors.toList());

        return getSalesResponse(saleDtos, totalSalesAmount);
    }

    private List<Sale> fetchSales(LocalDateTime startDate, LocalDateTime endDate, List<Long> productIds, ProductTypeEnum productType) {
        if (productIds != null && !productIds.isEmpty()) {
            return saleRepository.findByCreateDateBetweenAndProductIdIn(startDate, endDate, productIds);
        } else if (productType != null) {
            return saleRepository.findByCreateDateBetweenAndProductType(startDate, endDate, productType);
        } else {
            return saleRepository.findByCreateDateBetween(startDate, endDate);
        }
    }

    public List<SaleDto> createSale() {
        List<Sale> existingSales = saleRepository.findAll();
        List<Order> orders = orderRepository.findAll();

        List<Sale> salesToDelete = existingSales.stream()
                .filter(sale -> !isOrderExistInSales(orders, sale))
                .collect(Collectors.toList());

        saleRepository.deleteAll(salesToDelete);

        List<Sale> newSales = orders.stream()
                .flatMap(order -> order.getOrderItems().stream()
                        .filter(orderItem -> !isSaleExist(existingSales, order, orderItem))
                        .map(orderItem -> {
                            Product product = orderItem.getProduct();
                            return new Sale(order.getCreateDate(), product.getId(), orderItem.getPrice(), orderItem.getQuantity(), product.getProductType());
                        }))
                .collect(Collectors.toList());

        List<Sale> savedSales = saleRepository.saveAll(newSales);
        return savedSales.stream().map(this::convertToSaleDto).collect(Collectors.toList());
    }

    private boolean isSaleExist(List<Sale> existingSales, Order order, OrderItem orderItem) {
        return existingSales.stream().anyMatch(sale ->
                sale.getCreateDate().equals(order.getCreateDate())
                        && sale.getProductId().equals(orderItem.getProduct().getId())
                        && sale.getPrice() == orderItem.getPrice()
                        && sale.getQuantity() == orderItem.getQuantity());
    }

    private boolean isOrderExistInSales(List<Order> orders, Sale sale) {
        return orders.stream().anyMatch(order ->
                order.getOrderItems().stream().anyMatch(orderItem ->
                        sale.getCreateDate().equals(order.getCreateDate())
                                && sale.getProductId().equals(orderItem.getProduct().getId())
                                && sale.getPrice() == orderItem.getPrice()
                                && sale.getQuantity() == orderItem.getQuantity()));
    }

    private Map<String, Object> getSalesResponse(List<SaleDto> sales, int totalSalesAmount) {
        Map<String, Object> response = new HashMap<>();
        response.put("sales", sales);
        response.put("totalSalesAmount", totalSalesAmount);
        return response;
    }

    private SaleDto convertToSaleDto(Sale sale) {
        Product product = productRepository.findById(sale.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        return SaleDto.builder()
                .id(sale.getId())
                .createDate(sale.getCreateDate())
                .productId(sale.getProductId())
                .price(sale.getPrice())
                .quantity(sale.getQuantity())
                .productType(product.getProductType())
                .build();
    }
}
