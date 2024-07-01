package com.example.DWShopProject.service;

import com.example.DWShopProject.dto.SearchRequestDto;
import com.example.DWShopProject.dto.SearchResultDto;
import com.example.DWShopProject.dto.SearchResultsDto;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.ProductRepository;
import com.example.DWShopProject.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SearchService {

    private final ProductRepository productRepository;

    @Autowired
    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 상품 검색 메서드
    public SearchResultsDto searchProducts(SearchRequestDto searchRequestDto) {
        Specification<Product> spec = Specification.where(null);

        if (searchRequestDto.getSearchValue() != null && !searchRequestDto.getSearchValue().isEmpty()) {
            if ("productName".equalsIgnoreCase(searchRequestDto.getSearchFilter())) {
                // 상품명으로 검색
                spec = spec.and(ProductSpecification.hasProductName(searchRequestDto.getSearchValue()));
            } else if ("category".equalsIgnoreCase(searchRequestDto.getSearchFilter())) {
                // 카테고리로 검색
                spec = spec.and(ProductSpecification.hasCategory(getMatchingCategories(searchRequestDto.getSearchValue())));
            } else if ("all".equalsIgnoreCase(searchRequestDto.getSearchFilter())) {
                // 전체보기 (상품명, 설명, 카테고리로 검색)
                Specification<Product> nameSpec = ProductSpecification.hasProductName(searchRequestDto.getSearchValue());
                Specification<Product> descriptionSpec = ProductSpecification.hasProductDescription(searchRequestDto.getSearchValue());
                Specification<Product> categorySpec = ProductSpecification.hasCategory(getMatchingCategories(searchRequestDto.getSearchValue()));
                spec = spec.and(nameSpec.or(descriptionSpec).or(categorySpec));
            }
        }

        List<Product> products = productRepository.findAll(spec);
        List<SearchResultDto> resultDtos = products.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new SearchResultsDto(resultDtos, resultDtos.size());
    }

    // 검색 값과 일치하는 카테고리를 찾는 메서드
    private List<ProductTypeEnum> getMatchingCategories(String searchValue) {
        String searchValueLower = searchValue.toLowerCase();
        List<ProductTypeEnum> matchingCategories = new ArrayList<>();

        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            if (type.getDisplayName().toLowerCase().contains(searchValueLower) ||
                    type.getParentTypeEnum().getDisplayName().toLowerCase().contains(searchValueLower)) {
                matchingCategories.add(type);
            }
        }

        return matchingCategories;
    }

    // Product 엔티티를 SearchResultDto로 변환하는 메서드
    private SearchResultDto mapToDTO(Product product) {
        return SearchResultDto.builder()
                .id(product.getId())
                .productType(product.getProductType())
                .productName(product.getProductName())
                .price(product.getPrice())
                .explanation(product.getExplanation())
                .imageUrl(product.getImageUrl())
                .build();
    }
}