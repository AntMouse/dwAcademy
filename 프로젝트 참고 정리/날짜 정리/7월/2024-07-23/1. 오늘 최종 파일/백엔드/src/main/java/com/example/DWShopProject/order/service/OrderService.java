package com.example.DWShopProject.order.service;

import com.example.DWShopProject.account.dto.UserDTO;
import com.example.DWShopProject.order.dto.OrderDto;
import com.example.DWShopProject.order.dto.OrderItemDto;
import com.example.DWShopProject.account.entity.User;
import com.example.DWShopProject.order.entity.Order;
import com.example.DWShopProject.order.entity.OrderItem;
import com.example.DWShopProject.product.entity.Product;
import com.example.DWShopProject.order.enums.OrderStatus;
import com.example.DWShopProject.account.repository.UserRepository;
import com.example.DWShopProject.order.repository.OrderRepository;
import com.example.DWShopProject.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;


    public Order createOrder(Long userId, OrderDto orderDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        Order order = new Order();
        order.setUser(user);
        order.setRecipientName(orderDto.getRecipientName());
        order.setContactNumber(orderDto.getContactNumber());
        order.setDeliveryLocation(orderDto.getDeliveryLocation());
        order.setRequest(orderDto.getRequest());
        order.setCreateDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = new ArrayList<>();
        int totalPrice = 0;

        for (OrderItemDto itemDto : orderDto.getOrderItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없음"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(product.getPrice());

            totalPrice += product.getPrice() * itemDto.getQuantity();
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public List<OrderDto> getOrderListByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(order -> {
                    List<OrderItemDto> orderItems = order.getOrderItems().stream()
                            .map(item -> new OrderItemDto(
                                    item.getId(),
                                    item.getOrderProductSnapshot().getId(),
                                    item.getOrderProductSnapshot().getProductName(),
                                    item.getOrderProductSnapshot().getImageUrl(),
                                    item.getQuantity(),
                                    item.getPrice()
                            ))
                            .collect(Collectors.toList());

                    UserDTO userDto = convertToUserDto(order.getUser());

                    return new OrderDto(
                            order.getId(),
                            userDto, // userDto 추가
                            order.getRecipientName(),
                            order.getContactNumber(),
                            order.getDeliveryLocation(),
                            order.getCreateDate(),
                            order.getRequest(),
                            order.getTotalPrice(),
                            order.getStatus(), // OrderStatus 그대로 사용
                            orderItems
                    );
                })
                .collect(Collectors.toList());
    }


    //주문 상세보기 서비스

    public OrderDto getOrderDetailByIdAndUserId(Long orderId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        Order order = orderRepository.findByIdAndUser(orderId, user)
                .orElseThrow(() -> new RuntimeException("주문이 존재하지 않음"));

        return convertToOrderDto(order);
    }

    private OrderDto convertToOrderDto(Order order) {
        List<OrderItemDto> orderItems = order.getOrderItems().stream()
                .map(this::convertToOrderItemDto)
                .collect(Collectors.toList());

        UserDTO userDto = convertToUserDto(order.getUser());

        return OrderDto.builder()
                .id(order.getId())
                .recipientName(order.getRecipientName())
                .contactNumber(order.getContactNumber())
                .deliveryLocation(order.getDeliveryLocation())
                .createDate(order.getCreateDate())
                .request(order.getRequest())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .orderItems(orderItems)
                .user(userDto)  // 추가된 부분
                .build();
    }

    private UserDTO convertToUserDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .contact(user.getContact())
                .email(user.getEmail())
                .build();
    }

    private OrderItemDto convertToOrderItemDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getOrderProductSnapshot().getId())
                .productName(orderItem.getOrderProductSnapshot().getProductName())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .imageUrl(orderItem.getOrderProductSnapshot().getImageUrl())
                .build();
    }

    public void calculateTotalPrice(Order order) {
        int totalPrice = order.getOrderItems().stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.updateTotalPrice(totalPrice);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        // Order 엔티티를 데이터베이스에서 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));

        // 주문을 취소하고 관련된 OrderItem 및 OrderProductSnapshot을 삭제
        orderRepository.delete(order);
    }
}
