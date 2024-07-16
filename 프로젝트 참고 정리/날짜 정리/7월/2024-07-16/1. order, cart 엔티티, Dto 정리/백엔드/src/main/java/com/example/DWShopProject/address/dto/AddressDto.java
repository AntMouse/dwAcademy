package com.example.DWShopProject.address.dto;

import com.example.DWShopProject.address.entity.Address;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private Long id;
    private String postalCode;       // 우편번호
    private String primaryAddress;   // 기본 주소
    private String detailAddress;    // 상세 주소

    public AddressDto(Address address) {
        this.id = address.getId();
        this.postalCode = address.getPostalCode();
        this.primaryAddress = address.getPrimaryAddress();
        this.detailAddress = address.getDetailAddress();
    }
}