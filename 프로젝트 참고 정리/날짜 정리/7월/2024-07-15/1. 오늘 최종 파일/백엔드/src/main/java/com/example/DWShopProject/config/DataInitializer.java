//package com.example.DWShopProject.config;
//
//import com.example.DWShopProject.user.entity.User;
//import com.example.DWShopProject.user.repository.UserRepository;
//import com.example.DWShopProject.cart.entity.Cart;
//import com.example.DWShopProject.cart.entity.CartItem;
//import com.example.DWShopProject.cart.repository.CartItemRepository;
//import com.example.DWShopProject.cart.repository.CartRepository;
//import com.example.DWShopProject.user.dto.UserDto;
//import com.example.DWShopProject.order.enums.OrderStatus;
//import com.example.DWShopProject.product.enums.ProductType;
//import com.example.DWShopProject.order.entity.Order;
//import com.example.DWShopProject.order.entity.OrderItem;
//import com.example.DWShopProject.order.repository.OrderItemRepository;
//import com.example.DWShopProject.order.repository.OrderRepository;
//import com.example.DWShopProject.product.entity.Product;
//import com.example.DWShopProject.product.entity.ProductTypeMgmt;
//import com.example.DWShopProject.product.repository.ProductRepository;
//import com.example.DWShopProject.product.repository.ProductTypeMgmtRepository;
//import com.example.DWShopProject.security.enums.ApplicationRoleEnum;
//import com.example.DWShopProject.user.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Collections;
//import java.util.Random;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ProductTypeMgmtRepository productTypeMgmtRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        // 데이터 초기화
//        productRepository.deleteAll();
//        userRepository.deleteAll();
//        cartRepository.deleteAll();
//        cartItemRepository.deleteAll();
//
//
//        // ProductType 저장
//        List<ProductTypeMgmt> productTypeMgms = new ArrayList<>();
//        for (ProductType type : ProductType.values()) {
//            ProductTypeMgmt productTypeMgmt = new ProductTypeMgmt(type, type.getParentTypeEnum());
//            productTypeMgms.add(productTypeMgmt);
//        }
//        productTypeMgmtRepository.saveAll(productTypeMgms);
//
//        // 기본 데이터 생성
//        List<Product> products = new ArrayList<>();
//        String githubBaseUrl = "https://raw.githubusercontent.com/AntMouse/dwAcademy/main/ProjectData/dw_shop_app/images/products";
//
//        for (ProductType type : ProductType.values()) {
//            String parentTypePath = type.getParentTypeEnum().name().toLowerCase();
//            String typePath = type.name().toLowerCase();
//            for (int i = 1; i <= 10; i++) {
//                String displayName = type.getDisplayName().toLowerCase().replace(" ", "_");
//                String imageName = parentTypePath + "/" + typePath + "/" + displayName + "_" + i + ".jpg";
//                String imageUrl = githubBaseUrl + "/" + imageName;
//
//                Product product = Product.builder()
//                        .productType(type)
//                        .productName(type.getDisplayName() + " - Product " + i)
//                        .price(100 * i)
//                        .explanation("설명 " + type.getDisplayName() + " - Product " + i)
//                        .imageUrl("https://search.pstatic.net/sunny/?src=https%3A%2F%2Fwww.byslim.com%2Fweb%2Fanew%2Fjjh%2F2023%2F12%2F28%2F21_black_10.jpg&type=sc960_832")
//                        .createDate(LocalDateTime.now())
//                        .build();
//                products.add(product);
//            }
//        }
//
//        productRepository.saveAll(products);
//
//        // 회원 및 장바구니 생성
//        for (int i = 1; i <= 10; i++) {
//            UserDto userDto = UserDto.builder()
//                    .userType(ApplicationRoleEnum.USER)
//                    .userId("user" + i)
//                    .username("User " + i)
//                    .password("1234")
//                    .birthdate("1990-01-0" + i)
//                    .gender(i % 2 == 0 ? "Male" : "Female")
//                    .email("user" + i + "@example.com")
//                    .contact("123-456-789" + i)
//                    .build();
//
//            userService.signUp(userDto);
//        }
//
//        UserDto adminDto = UserDto.builder()
//                .userType(ApplicationRoleEnum.USER)
//                .userId("admin")
//                .username("Admin")
//                .password("1234")
//                .birthdate("1980-01-01")
//                .gender("Male")
//                .email("admin@example.com")
//                .build();
//
//        userService.createAdmin(adminDto);
//
//        List<Cart> carts = cartRepository.findAll();
//
//        List<CartItem> cartItems = new ArrayList<>();
//        int productIndex = 0;
//        for (Cart cart : carts) {
//            List<Product> shuffledProducts = new ArrayList<>(products);
//            Collections.shuffle(shuffledProducts);
//            for (int j = 0; j < 5; j++) {
//                Product product = shuffledProducts.get(j);
//                CartItem cartItem = CartItem.builder()
//                        .cart(cart)
//                        .product(product)
//                        .quantity(1)
//                        .build();
//                cartItems.add(cartItem);
//            }
//        }
//        cartItemRepository.saveAll(cartItems);
//
//        // 주문 생성
//        List<User> users1 = userRepository.findAll();
//        Random random = new Random();
//
//        for (User user : users1) {
//            for (int j = 0; j < 5; j++) { // 각 회원마다 5개의 주문 생성
//                List<OrderItem> orderItems = new ArrayList<>();
//                int totalPrice = 0;
//
//                for (int k = 0; k < random.nextInt(5) + 1; k++) { // 각 주문마다 1~5개의 랜덤 상품 추가
//                    Product product = products.get(random.nextInt(products.size()));
//                    int quantity = random.nextInt(5) + 1;
//
//                    OrderItem orderItem = OrderItem.builder()
//                            .product(product)
//                            .quantity(quantity)
//                            .price(product.getPrice())
//                            .build();
//
//                    orderItems.add(orderItem);
//                    totalPrice += product.getPrice() * quantity;
//                }
//
//                Order order = Order.builder()
//                        .user(user)
//                        .orderItems(orderItems)
//                        .recipientName(user.getUserName())
//                        .contactNumber(user.getContact())
//                        .deliveryLocation("주소 " + user.getUserId())
//                        .createDate(LocalDateTime.now())
//                        .request("요청사항 " + j)
//                        .totalPrice(totalPrice)
//                        .status(OrderStatus.PENDING)
//                        .build();
//
//                orderRepository.save(order);
//
//                for (OrderItem orderItem : orderItems) {
//                    orderItem.setOrder(order);
//                    orderItemRepository.save(orderItem);
//                }
//            }
//
//        }
//    }
//}
