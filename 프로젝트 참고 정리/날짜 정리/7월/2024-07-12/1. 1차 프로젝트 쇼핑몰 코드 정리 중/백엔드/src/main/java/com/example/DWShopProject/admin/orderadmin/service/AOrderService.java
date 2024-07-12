package com.example.DWShopProject.admin.orderadmin.service;

import com.example.DWShopProject.admin.orderadmin.repository.AOrderItemRepository;
import com.example.DWShopProject.admin.orderadmin.repository.AOrderRepository;
import com.example.DWShopProject.user.repository.UserRepository;
import com.example.DWShopProject.admin.orderadmin.dto.AOrderDto;
import com.example.DWShopProject.admin.orderadmin.dto.AOrderItemDto;
import com.example.DWShopProject.order.entity.Order;
import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.order.entity.OrderItem;
import com.example.DWShopProject.product.entity.Product;
import com.example.DWShopProject.order.enums.OrderStatus;
import com.example.DWShopProject.common.exception.ResourceNotFoundException;
import com.example.DWShopProject.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AOrderService {
    private static final Logger logger = LoggerFactory.getLogger(AOrderService.class);

    private final AOrderRepository orderRepository;
    private final AOrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public AOrderService(AOrderRepository orderRepository, AOrderItemRepository orderItemRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public AOrderDto createOrder(AOrderDto orderDto) {
        logger.info("Creating order for user ID: {}", orderDto.getUserId());

        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = Order.builder()
                .user(user)
                .recipientName(orderDto.getRecipientName())
                .contactNumber(orderDto.getContactNumber())
                .deliveryLocation(orderDto.getDeliveryLocation())
                .createDate(LocalDateTime.now())
                .request(orderDto.getRequest())
                .totalPrice(orderDto.getTotalPrice())
                .status(OrderStatus.PENDING)
                .build();

        // OrderItems 병합 처리
        Map<Long, OrderItem> orderItemMap = new HashMap<>();
        for (AOrderItemDto itemDto : orderDto.getOrderItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            if (orderItemMap.containsKey(itemDto.getProductId())) {
                OrderItem existingOrderItem = orderItemMap.get(itemDto.getProductId());
                existingOrderItem.setQuantity(existingOrderItem.getQuantity() + itemDto.getQuantity());
            } else {
                OrderItem orderItem = OrderItem.builder()
                        .order(order)
                        .product(product)
                        .quantity(itemDto.getQuantity())
                        .price(itemDto.getPrice())
                        .build();
                orderItemMap.put(itemDto.getProductId(), orderItem);
            }
        }

        List<OrderItem> orderItems = new ArrayList<>(orderItemMap.values());
        orderItems.forEach(order::addOrderItem);

        Order savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return toOrderDto(savedOrder);
    }

    // 주문 ID로 주문 정보를 가져오는 메서드
    @Transactional(readOnly = true)
    public Optional<AOrderDto> getOrderById(Long id) {
        logger.info("Fetching order by ID: {}", id);
        return orderRepository.findById(id)
                .map(this::toOrderDto);
    }

    // 모든 주문 정보를 가져오는 메서드
    @Transactional(readOnly = true)
    public List<AOrderDto> getAllOrders() {
        logger.info("Fetching all orders");
        return orderRepository.findAll().stream()
                .map(this::toOrderDto)
                .collect(Collectors.toList());
    }

    // 모든 주문 상태를 가져오는 메서드
    @Transactional(readOnly = true)
    public List<OrderStatus> getAllOrderStatuses() {
        logger.info("Fetching all order statuses");
        return Arrays.asList(OrderStatus.values());
    }

    // 주문 상태를 업데이트하는 메서드
    @Transactional
    public AOrderDto updateOrderStatus(Long id, String status) {
        logger.info("Updating status of order ID: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.updateOrderDetails(null, null, null, null, -1, OrderStatus.valueOf(status));
        order = orderRepository.save(order);
        return toOrderDto(order);
    }

    // 여러 주문 상태를 업데이트하는 메서드
    @Transactional
    public List<AOrderDto> updateOrderStatuses(List<Long> ids, String status) {
        List<Order> orders = orderRepository.findAllById(ids);
        orders.forEach(order -> order.updateOrderDetails(null, null, null, null, -1, OrderStatus.valueOf(status)));
        orderRepository.saveAll(orders);
        return orders.stream().map(this::toOrderDto).collect(Collectors.toList());
    }

    // 주문 정보를 업데이트하는 메서드
    @Transactional
    public AOrderDto updateOrder(Long id, AOrderDto orderDto) {
        logger.info("Updating order with ID: {}", id);

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        existingOrder.updateOrderDetails(
                orderDto.getRecipientName(),
                orderDto.getContactNumber(),
                orderDto.getDeliveryLocation(),
                orderDto.getRequest(),
                orderDto.getTotalPrice(),
                orderDto.getStatus()
        );

        List<OrderItem> updatedOrderItems = orderDto.getOrderItems() != null ? orderDto.getOrderItems().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            return OrderItem.builder()
                    .order(existingOrder)
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .price(itemDto.getPrice())
                    .build();
        }).collect(Collectors.toList()) : new ArrayList<>();

        existingOrder.getOrderItems().clear();
        updatedOrderItems.forEach(existingOrder::addOrderItem);

        Order savedOrder = orderRepository.save(existingOrder);
        orderItemRepository.saveAll(updatedOrderItems);

        return toOrderDto(savedOrder);
    }

    // 주문 항목 수량을 업데이트하는 메서드
    @Transactional
    public AOrderDto updateOrderItemQuantity(Long orderId, Long orderItemId, int quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found"));

        orderItem.updateOrderItemInfo(quantity, orderItem.getPrice());
        orderItemRepository.save(orderItem);

        return toOrderDto(order);
    }

    // 주문을 삭제하는 메서드
    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        orderRepository.delete(order);
        logger.info("Deleted order with ID: {}", id);
    }

    // 여러 주문을 삭제하는 메서드
    @Transactional
    public void deleteOrders(List<Long> ids) {
        List<Order> orders = orderRepository.findAllById(ids);
        if (orders.size() != ids.size()) {
            throw new ResourceNotFoundException("One or more orders not found");
        }
        orderRepository.deleteAll(orders);
        logger.info("Deleted orders with IDs: {}", ids);
    }

    // Order 엔티티를 AOrderDto로 변환하는 메서드
    private AOrderDto toOrderDto(Order order) {
        List<AOrderItemDto> items = order.getOrderItems().stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());

        return AOrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
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

    // OrderItem 엔티티를 AOrderItemDto로 변환하는 메서드
    private AOrderItemDto toOrderItemDto(OrderItem orderItem) {
        return AOrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .productName(orderItem.getProduct().getProductName()) // 추가된 필드
                .build();
    }
}