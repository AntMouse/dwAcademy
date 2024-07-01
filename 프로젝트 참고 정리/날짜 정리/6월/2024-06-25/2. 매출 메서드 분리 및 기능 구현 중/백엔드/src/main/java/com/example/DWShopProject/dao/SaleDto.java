package com.example.DWShopProject.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {
    private Long id;
    private LocalDateTime createDate;
    private Long productId;
    private int price;
    private int quantity;
}
