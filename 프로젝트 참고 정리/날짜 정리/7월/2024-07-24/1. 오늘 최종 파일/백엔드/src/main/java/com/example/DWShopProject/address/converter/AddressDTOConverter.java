package com.example.DWShopProject.address.converter;

import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.address.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressDTOConverter {

    public AddressDTO convertToDto(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDTO.builder()
                .id(address.getId())
                .postalCode(address.getPostalCode())
                .primaryAddress(address.getPrimaryAddress())
                .detailAddress(address.getDetailAddress())
                .build();
    }

    public Address convertToEntity(AddressDTO addressDto) {
        if (addressDto == null) {
            return null;
        }
        return Address.builder()
                .id(addressDto.getId())
                .postalCode(addressDto.getPostalCode())
                .primaryAddress(addressDto.getPrimaryAddress())
                .detailAddress(addressDto.getDetailAddress())
                .build();
    }
}
