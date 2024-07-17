package com.example.DWShopProject.address.entity;

import com.example.DWShopProject.account.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private User user;

    @Column(nullable = false)
    private String postalCode;       // 우편번호

    @Column(nullable = false)
    private String primaryAddress;   // 기본 주소

    @Column(nullable = false)
    private String detailAddress;    // 상세 주소

    public Address(String postalCode, String primaryAddress, String detailAddress) {
        this.postalCode = postalCode;
        this.primaryAddress = primaryAddress;
        this.detailAddress = detailAddress;
    }

    public void updateAddressDetails(String postalCode, String primaryAddress, String detailAddress) {
        if (postalCode != null && !postalCode.isEmpty()) {
            this.postalCode = postalCode;
        }
        if (primaryAddress != null && !primaryAddress.isEmpty()) {
            this.primaryAddress = primaryAddress;
        }
        if (detailAddress != null && !detailAddress.isEmpty()) {
            this.detailAddress = detailAddress;
        }
    }

}
