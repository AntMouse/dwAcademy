package com.example.DWShopProject.order.controller;

import com.example.DWShopProject.order.dto.OrderDto;
import com.example.DWShopProject.user.repository.UserRepository;
import com.example.DWShopProject.order.repository.OrderRepository;
import com.example.DWShopProject.product.repository.ProductRepository;
import com.example.DWShopProject.security.userdetails.UserDetailsImpl;
import com.example.DWShopProject.order.service.OrderService;
import com.example.DWShopProject.paypal.service.PayPalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Autowired
    private PayPalService payPalService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;


    //---------------------주문생성 테스트
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody OrderDto orderDto) {
        Long userId = userDetails.getUser().getId();
        orderService.createOrder(userId, orderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderDto>> getOrderList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        List<OrderDto> orderList = orderService.getOrderListByUserId(userId);
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderDetail(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        Long userId = userDetails.getUser().getId();
        OrderDto order = orderService.getOrderDetailByIdAndUserId(id, userId);
        return ResponseEntity.ok(order);
    }





}
