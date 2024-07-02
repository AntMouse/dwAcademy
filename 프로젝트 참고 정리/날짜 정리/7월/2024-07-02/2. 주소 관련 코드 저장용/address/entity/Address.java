package com.example.DWTransferScoutProject.address.entity;

import com.example.DWTransferScoutProject.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipientName;
    private String contactNumber;
    private String deliveryLocation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public void updateAddressInfo(String recipientName, String contactNumber, String deliveryLocation) {
        if (recipientName != null) {
            this.recipientName = recipientName;
        }
        if (contactNumber != null) {
            this.contactNumber = contactNumber;
        }
        if (deliveryLocation != null) {
            this.deliveryLocation = deliveryLocation;
        }
    }
}
