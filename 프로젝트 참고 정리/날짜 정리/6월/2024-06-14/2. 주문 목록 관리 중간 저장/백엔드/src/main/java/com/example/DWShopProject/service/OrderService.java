package com.example.DWShopProject.service;

import com.example.DWShopProject.dto.OrderDto;
import com.example.DWShopProject.dto.OrderItemDto;
import com.example.DWShopProject.entity.Order;
import com.example.DWShopProject.entity.OrderItem;
import com.example.DWShopProject.entity.Member;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.enums.OrderStatus;
import com.example.DWShopProject.exception.ResourceNotFoundException;
import com.example.DWShopProject.repository.OrderRepository;
import com.example.DWShopProject.repository.MemberRepository;
import com.example.DWShopProject.repository.ProductRepository;
import com.example.DWShopProject.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, MemberRepository memberRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        logger.info("Creating order for member ID: {}", orderDto.getMemberId());

        Member member = memberRepository.findById(orderDto.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        Order order = Order.builder()
                .member(member)
                .recipientName(orderDto.getRecipientName())
                .contactNumber(orderDto.getContactNumber())
                .deliveryLocation(orderDto.getDeliveryLocation())
                .createDate(LocalDateTime.now())
                .request(orderDto.getRequest())
                .totalPrice(orderDto.getTotalPrice())
                .status(OrderStatus.PENDING)
                .build();

        List<OrderItem> orderItems = orderDto.getOrderItems().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            return OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .price(itemDto.getPrice())
                    .build();
        }).collect(Collectors.toList());

        orderItems.forEach(order::addOrderItem);

        Order savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return toOrderDto(savedOrder);
    }

    @Transactional(readOnly = true)
    public Optional<OrderDto> getOrderById(Long id) {
        logger.info("Fetching order by ID: {}", id);
        return orderRepository.findById(id)
                .map(this::toOrderDto);
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        logger.info("Fetching all orders");
        return orderRepository.findAll().stream()
                .map(this::toOrderDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderStatus> getAllOrderStatuses() {
        logger.info("Fetching all order statuses");
        return Arrays.asList(OrderStatus.values());
    }

    @Transactional
    public OrderDto updateOrderStatus(Long id, OrderStatus status) {
        logger.info("Updating status of order ID: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.updateOrderInfo(null, null, null, null, -1, status);
        order = orderRepository.save(order);
        return toOrderDto(order);
    }

    @Transactional
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        logger.info("Updating order with ID: {}", id);
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        existingOrder.updateOrderInfo(
                orderDto.getRecipientName(),
                orderDto.getContactNumber(),
                orderDto.getDeliveryLocation(),
                orderDto.getRequest(),
                orderDto.getTotalPrice(),
                orderDto.getStatus()
        );

        List<OrderItem> updatedOrderItems = orderDto.getOrderItems().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            return OrderItem.builder()
                    .order(existingOrder)
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .price(itemDto.getPrice())
                    .build();
        }).collect(Collectors.toList());

        existingOrder.getOrderItems().clear();
        updatedOrderItems.forEach(existingOrder::addOrderItem);

        Order savedOrder = orderRepository.save(existingOrder);
        orderItemRepository.saveAll(updatedOrderItems);

        return toOrderDto(savedOrder);
    }

    @Transactional
    public OrderDto updateOrderItemQuantity(Long orderId, Long orderItemId, int quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found"));

        orderItem.updateOrderItemInfo(quantity, orderItem.getPrice());
        orderItemRepository.save(orderItem);

        return toOrderDto(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        orderRepository.delete(order);
        logger.info("Deleted order with ID: {}", id);
    }

    private OrderDto toOrderDto(Order order) {
        List<OrderItemDto> items = order.getOrderItems().stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());

        return OrderDto.builder()
                .id(order.getId())
                .memberId(order.getMember().getId())
                .recipientName(order.getRecipientName())
                .contactNumber(order.getContactNumber())
                .deliveryLocation(order.getDeliveryLocation())
                .createDate(order.getCreateDate())
                .request(order.getRequest())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .orderItems(items)
                .build();
    }

    private OrderItemDto toOrderItemDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .productName(orderItem.getProduct().getProductName()) // 추가된 필드
                .build();
    }

}
