package com.example.DWShopProject.config;

import com.example.DWShopProject.dto.MemberDto;
import com.example.DWShopProject.entity.*;
import com.example.DWShopProject.enums.OrderStatus;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.*;
import com.example.DWShopProject.security.MemberRoleEnum;
import com.example.DWShopProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductTypeMgmtRepository productTypeMgmtRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // 데이터 초기화
        productRepository.deleteAll();
        memberRepository.deleteAll();
        cartRepository.deleteAll();
        cartItemRepository.deleteAll();
        orderRepository.deleteAll();
        orderItemRepository.deleteAll();;

        // ProductType 저장
        List<ProductTypeMgmt> productTypeMgms = new ArrayList<>();
        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            ProductTypeMgmt productTypeMgmt = new ProductTypeMgmt(type, type.getParentTypeEnum());
            productTypeMgms.add(productTypeMgmt);
        }
        productTypeMgmtRepository.saveAll(productTypeMgms);

        // 기본 데이터 생성
        List<Product> products = new ArrayList<>();
        String githubBaseUrl = "https://raw.githubusercontent.com/AntMouse/dwAcademy/main/ProjectData/dw_shop_app/images/products";
        Random random = new Random(); // Random 객체 생성
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime[] createDates = {
                now.minusDays(1),
                now.minusDays(2),
                now.minusWeeks(1),
                now.minusWeeks(2),
                now.minusMonths(1),
                now.minusMonths(2),
                now.minusMonths(6),
                now.minusMonths(9),
                now.minusYears(1),
                now.minusYears(2)
        };

        Map<ParentTypeEnum, Integer> parentTypeNumber = new HashMap<>();
        parentTypeNumber.put(ParentTypeEnum.TOPS, 1);
        parentTypeNumber.put(ParentTypeEnum.BOTTOMS, 2);
        parentTypeNumber.put(ParentTypeEnum.OUTERWEAR, 3);
        parentTypeNumber.put(ParentTypeEnum.SHOES, 4);
        parentTypeNumber.put(ParentTypeEnum.ACCESSORIES, 5);

        Map<ParentTypeEnum, Integer> parentTypeCounter = new HashMap<>();
        for (ParentTypeEnum parentType : ParentTypeEnum.values()) {
            parentTypeCounter.put(parentType, 0);
        }

        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            ParentTypeEnum parentType = type.getParentTypeEnum();
            int parentNumber = parentTypeNumber.get(parentType);
            parentTypeCounter.put(parentType, parentTypeCounter.get(parentType) + 1);
            int typeNumber = parentTypeCounter.get(parentType);

            String parentTypePath = parentType.name().toLowerCase();
            String typePath = type.name().toLowerCase();
            for (int i = 1; i <= 10; i++) { // 총 10개의 날짜에 맞춰 생성
                String displayName = type.getDisplayName().toLowerCase().replace(" ", "_");
                String imageName = parentTypePath + "/" + typePath + "/" + displayName + "_" + i + ".jpg";
                String imageUrl = githubBaseUrl + "/" + imageName;
                int randomPrice = 100 + random.nextInt(9901); // 100 ~ 10000 사이의 랜덤 가격

                String productName = parentNumber + "-" + typeNumber + " 상품 " + i;

                Product product = Product.builder()
                        .productType(type)
                        .productName(productName)
                        .price(randomPrice) // 랜덤 가격 설정
                        .explanation("설명 " + productName)
                        .imageUrl(imageUrl)
                        .createDate(createDates[i - 1]) // 다양한 날짜 설정
                        .build();
                products.add(product);
            }
        }
        productRepository.saveAll(products);

        // 외부 파일에서 데이터 읽기
        List<String> names = readLinesFromFile("src/main/resources/data/names.txt");
        List<String> emails = readLinesFromFile("src/main/resources/data/emails.txt");
        List<String> phoneNumbers = readLinesFromFile("src/main/resources/data/phoneNumbers.txt");
        List<String> addresses = readLinesFromFile("src/main/resources/data/addresses.txt");

        // 회원 및 장바구니 생성
        for (int i = 0; i < 120; i++) {
            MemberDto memberDto = MemberDto.builder()
                    .memberType(MemberRoleEnum.ROLE_USER)
                    .memberId(emails.get(i % emails.size()))
                    .memberName(names.get(i % names.size()))
                    .password("1234")
                    .birthdate(String.format("1990-%02d-%02d", (i % 12) + 1, (i % 28) + 1))
                    .gender(i % 2 == 0 ? "Male" : "Female")
                    .email(emails.get(i % emails.size()) + "@example.com")
                    .contact(phoneNumbers.get(i % phoneNumbers.size()))
                    .address(addresses.get(i % addresses.size()))
                    .build();

            memberService.signUp(memberDto);
        }

        List<Cart> carts = cartRepository.findAll();

        List<CartItem> cartItems = new ArrayList<>();
        int productIndex = 0;
        for (Cart cart : carts) {
            List<Product> shuffledProducts = new ArrayList<>(products);
            Collections.shuffle(shuffledProducts);
            for (int j = 0; j < 5; j++) {
                Product product = shuffledProducts.get(j);
                CartItem cartItem = CartItem.builder()
                        .cart(cart)
                        .product(product)
                        .quantity(1)
                        .build();
                cartItems.add(cartItem);
            }
        }
        cartItemRepository.saveAll(cartItems);

        // 주문 생성
        List<String> recipientNames = readLinesFromFile("src/main/resources/data/recipientName.txt");
        List<String> contactNumbers = readLinesFromFile("src/main/resources/data/contactNumber.txt");
        List<String> deliveryLocations = readLinesFromFile("src/main/resources/data/deliveryLocation.txt");

        List<Member> members1 = memberRepository.findAll();
        Random random2 = new Random();

        int recipientIndex = 0; // 수취인 정보 인덱스 초기화

        for (Member member : members1) {
            // 한 회원당 동일한 수취인 정보, 연락처, 배송지 설정
            String recipientName = recipientNames.get(recipientIndex % recipientNames.size());
            String contactNumber = contactNumbers.get(recipientIndex % contactNumbers.size());
            String deliveryLocation = deliveryLocations.get(recipientIndex % deliveryLocations.size());
            recipientIndex++; // 다음 회원을 위해 인덱스 증가

            for (int j = 0; j < 5; j++) { // 각 회원마다 5개의 주문 생성
                List<OrderItem> orderItems = new ArrayList<>();
                int totalPrice = 0;
                Map<Long, OrderItem> orderItemMap = new HashMap<>(); // 제품 ID를 키로 사용하는 Map

                for (int k = 0; k < random2.nextInt(5) + 1; k++) { // 각 주문마다 1~5개의 랜덤 상품 추가
                    Product product = products.get(random2.nextInt(products.size()));
                    int quantity = random2.nextInt(5) + 1;

                    // 동일한 제품 ID가 이미 존재하는지 확인
                    if (orderItemMap.containsKey(product.getId())) {
                        OrderItem existingOrderItem = orderItemMap.get(product.getId());
                        existingOrderItem.setQuantity(existingOrderItem.getQuantity() + quantity);
                    } else {
                        OrderItem orderItem = OrderItem.builder()
                                .product(product)
                                .quantity(quantity)
                                .price(product.getPrice())
                                .build();
                        orderItems.add(orderItem);
                        orderItemMap.put(product.getId(), orderItem); // Map에 추가
                    }

                    totalPrice += product.getPrice() * quantity;
                }

                Order order = Order.builder()
                        .member(member)
                        .orderItems(new ArrayList<>(orderItemMap.values())) // Map의 값을 리스트로 변환하여 설정
                        .recipientName(recipientName) // 텍스트 파일에서 가져온 수취인 정보 설정
                        .contactNumber(contactNumber) // 텍스트 파일에서 가져온 연락처 설정
                        .deliveryLocation(deliveryLocation) // 텍스트 파일에서 가져온 배송지 설정
                        .createDate(LocalDateTime.now())
                        .request("요청사항 " + j)
                        .totalPrice(totalPrice)
                        .status(OrderStatus.PENDING)
                        .build();

                orderRepository.save(order);

                for (OrderItem orderItem : orderItems) {
                    orderItem.setOrder(order);
                    orderItemRepository.save(orderItem);
                }
            }
        }
    }

    private List<String> readLinesFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
