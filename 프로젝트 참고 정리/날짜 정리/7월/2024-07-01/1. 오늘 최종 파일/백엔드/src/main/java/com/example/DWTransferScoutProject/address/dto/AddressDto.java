package com.example.DWTransferScoutProject.address.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddressDto {
    private String recipientName;
    private String contactNumber;
    private String deliveryLocation;
}
