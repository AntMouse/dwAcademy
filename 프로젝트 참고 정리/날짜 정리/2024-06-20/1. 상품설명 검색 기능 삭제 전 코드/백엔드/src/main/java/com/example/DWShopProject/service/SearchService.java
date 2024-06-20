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

    public SearchResultsDto searchProducts(SearchRequestDto searchRequestDto) {
        Specification<Product> spec = Specification.where(null);

        if (searchRequestDto.getSearchValue() != null && !searchRequestDto.getSearchValue().isEmpty()) {
            if ("productName".equalsIgnoreCase(searchRequestDto.getSearchFilter())) {
                spec = spec.and(ProductSpecification.hasProductName(searchRequestDto.getSearchValue()));
            } else if ("category".equalsIgnoreCase(searchRequestDto.getSearchFilter())) {
                String searchValueLower = searchRequestDto.getSearchValue().toLowerCase();
                List<ProductTypeEnum> matchingCategories = new ArrayList<>();
                for (ProductTypeEnum type : ProductTypeEnum.values()) {
                    // 서브 타입 검색
                    if (type.getDisplayName().toLowerCase().contains(searchValueLower)) {
                        matchingCategories.add(type);
                    }
                }

                if (matchingCategories.isEmpty()) {
                    // 메인 타입 검색
                    for (ParentTypeEnum parentType : ParentTypeEnum.values()) {
                        if (parentType.getDisplayName().toLowerCase().contains(searchValueLower)) {
                            for (ProductTypeEnum type : ProductTypeEnum.values()) {
                                if (type.getParentTypeEnum() == parentType) {
                                    matchingCategories.add(type);
                                }
                            }
                        }
                    }
                }

                if (!matchingCategories.isEmpty()) {
                    spec = spec.and(ProductSpecification.hasCategory(matchingCategories));
                } else {
                    throw new IllegalArgumentException("Invalid category: " + searchRequestDto.getSearchValue());
                }
            } else if ("all".equalsIgnoreCase(searchRequestDto.getSearchFilter())) { // 전체보기 필터
                spec = spec.and(ProductSpecification.hasProductNameOrDescription(searchRequestDto.getSearchValue()));
            }
        }

        List<Product> products = productRepository.findAll(spec);
        List<SearchResultDto> resultDtos = products.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new SearchResultsDto(resultDtos, resultDtos.size());
    }

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
