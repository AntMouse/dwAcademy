package com.example.DWShopProject.service;

import com.example.DWShopProject.dto.SearchRequestDto;
import com.example.DWShopProject.dto.SearchResponseDto;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchService {
    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    private final ProductRepository productRepository;

    @Autowired
    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<SearchResponseDto> searchProducts(LocalDateTime startDate, SearchRequestDto searchRequestDto) {
        logger.debug("Search Request: {}", searchRequestDto);

        ProductTypeEnum categoryEnum = null;
        if ("category".equals(searchRequestDto.getSearchType()) && searchRequestDto.getCategory() != null && !searchRequestDto.getCategory().isEmpty()) {
            try {
                categoryEnum = ProductTypeEnum.valueOf(searchRequestDto.getCategory());
                logger.debug("Parsed category enum: {}", categoryEnum);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid category type: " + searchRequestDto.getCategory());
            }
        }

        List<Product> products = productRepository.searchByDateAndConditions(
                startDate,
                searchRequestDto.getKeyword(),
                searchRequestDto.getSearchType(),
                categoryEnum
        );

        logger.debug("Found products: {}", products);

        return products.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public LocalDateTime calculateStartDate(String dateFilter) {
        LocalDateTime now = LocalDateTime.now();
        switch (dateFilter) {
            case "1day":
                return now.minusDays(1);
            case "1week":
                return now.minusWeeks(1);
            case "1month":
                return now.minusMonths(1);
            case "6months":
                return now.minusMonths(6);
            case "1year":
                return now.minusYears(1);
            case "all":
                return LocalDateTime.MIN; // 전체보기의 경우 가능한 최소 날짜로 설정
            default:
                throw new IllegalArgumentException("Invalid date filter");
        }
    }

    private SearchResponseDto mapToResponseDTO(Product product) {
        return SearchResponseDto.builder()
                .id(product.getId())
                .productType(product.getProductType()) // Enum을 그대로 사용
                .productName(product.getProductName())
                .price(product.getPrice())
                .explanation(product.getExplanation())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
