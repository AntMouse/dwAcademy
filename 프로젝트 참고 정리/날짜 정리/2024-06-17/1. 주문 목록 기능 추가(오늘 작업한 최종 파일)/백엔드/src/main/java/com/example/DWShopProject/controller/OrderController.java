package com.example.DWShopProject.controller;

import com.example.DWShopProject.dto.OrderDto;
import com.example.DWShopProject.exception.ResourceNotFoundException;
import com.example.DWShopProject.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@Validated
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto orderDto = orderService.getOrderById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> statusMap) {
        String status = statusMap.get("status");
        OrderDto updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("/status")
    public ResponseEntity<List<OrderDto>> updateOrderStatuses(@RequestBody Map<String, Object> requestBody) {
        List<Integer> orderIds = (List<Integer>) requestBody.get("orderIds");
        String status = (String) requestBody.get("status");

        // List<Integer>를 List<Long>으로 변환
        List<Long> longOrderIds = orderIds.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        List<OrderDto> updatedOrders = orderService.updateOrderStatuses(longOrderIds, status);
        return ResponseEntity.ok(updatedOrders);
    }

    @PutMapping("/{orderId}/items/{orderItemId}/quantity")
    public ResponseEntity<OrderDto> updateOrderItemQuantity(
            @PathVariable Long orderId,
            @PathVariable Long orderItemId,
            @RequestParam int quantity) {
        OrderDto updatedOrder = orderService.updateOrderItemQuantity(orderId, orderItemId, quantity);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOrders(@RequestBody List<Long> orderIds) {
        orderService.deleteOrders(orderIds);
        return ResponseEntity.noContent().build();
    }
}
