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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

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

        order = orderRepository.save(order);

        for (OrderItemDto itemDto : orderDto.getOrderItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .price(itemDto.getPrice())
                    .build();
            order.addOrderItem(orderItem);
        }

        order = orderRepository.save(order);  // OrderItem 추가 후 다시 저장

        return mapToDTO(order);
    }


    @Transactional(readOnly = true)
    public Optional<OrderDto> getOrderById(Long id) {
        logger.info("Fetching order by ID: {}", id);
        return orderRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        logger.info("Fetching all orders");
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public OrderDto updateOrderStatus(Long id, OrderStatus status) {
        logger.info("Updating status of order ID: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setStatus(status);
        order = orderRepository.save(order);
        return mapToDTO(order);
    }

    private OrderDto mapToDTO(Order order) {
        List<OrderItemDto> items = order.getOrderItems().stream()
                .map(item -> OrderItemDto.builder()
                        .id(item.getId())
                        .productId(item.getProduct().getId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build())
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

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        logger.info("Updating order with ID: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.updateOrderInfo(
                orderDto.getRecipientName(),
                orderDto.getContactNumber(),
                orderDto.getDeliveryLocation(),
                orderDto.getRequest(),
                orderDto.getTotalPrice(),
                orderDto.getStatus()
        );

        Order updatedOrder = orderRepository.save(order);
        return mapToDTO(updatedOrder);
    }

}
