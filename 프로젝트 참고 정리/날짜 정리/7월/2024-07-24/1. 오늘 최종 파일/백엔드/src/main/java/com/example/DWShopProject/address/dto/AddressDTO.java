package com.example.DWShopProject.address.dto;

import com.example.DWShopProject.address.entity.Address;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    private Long id;
    private String postalCode;       // 우편번호
    private String primaryAddress;   // 기본 주소
    private String detailAddress;    // 상세 주소
}