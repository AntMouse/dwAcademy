package com.example.DWShopProject.service;

import com.example.DWShopProject.dto.SearchDto;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.entity.ProductTypeMgmt; // ProductTypeMgmt 클래스 import
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.ProductRepository;
import com.example.DWShopProject.repository.ProductTypeMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeMgmtRepository productTypeMgmtRepository;

    // 상품명으로 검색
    public List<SearchDto> searchProductsByName(String keyword) {
        List<Product> products = productRepository.searchByNameContaining(keyword);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // 전체보기 검색 (상품명과 설명을 포함)
    public List<SearchDto> searchProductsAll(String keyword) {
        List<Product> products = productRepository.searchByNameOrDescriptionContaining(keyword);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // 카테고리로 검색
    public List<SearchDto> searchProductsByCategory(String keyword) {
        // 메인 타입과 서브 타입을 모두 확인
        List<ProductTypeEnum> productTypeEnums = findProductTypesByDisplayName(keyword);

        // 서브 타입 목록에 해당하는 상품 검색
        List<Product> products = productRepository.searchByProductTypes(productTypeEnums);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // ProductTypeEnum과 ParentTypeEnum의 displayName을 통해 ProductTypeEnum 목록을 찾는 메서드
    private List<ProductTypeEnum> findProductTypesByDisplayName(String displayName) {
        List<ProductTypeEnum> productTypeEnums = new ArrayList<>();

        // 서브 타입 확인
        productTypeEnums.addAll(
                ProductTypeEnum.stream()
                        .filter(type -> type.getDisplayName().equalsIgnoreCase(displayName))
                        .collect(Collectors.toList())
        );

        // 메인 타입 확인
        for (ParentTypeEnum parentType : ParentTypeEnum.values()) {
            if (parentType.getDisplayName().equalsIgnoreCase(displayName)) {
                productTypeEnums.addAll(
                        productTypeMgmtRepository.findByParentType(parentType).stream()
                                .map(ProductTypeMgmt::getProductType)
                                .collect(Collectors.toList())
                );
            }
        }

        return productTypeEnums;
    }

    // Product 엔티티를 SearchDto로 변환하는 메서드
    private SearchDto convertToDto(Product product) {
        SearchDto searchDto = new SearchDto();
        searchDto.setId(product.getId());
        searchDto.setName(product.getProductName());
        searchDto.setDescription(product.getExplanation());
        searchDto.setPrice((double) product.getPrice());
        return searchDto;
    }
}
