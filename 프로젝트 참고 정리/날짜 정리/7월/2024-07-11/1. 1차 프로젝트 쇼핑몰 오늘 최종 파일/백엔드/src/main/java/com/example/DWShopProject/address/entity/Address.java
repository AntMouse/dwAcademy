package com.example.DWShopProject.address.entity;

import com.example.DWShopProject.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
@Table(name = "addresses")
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

    public Address(Long id, String recipientName, String contactNumber, String deliveryLocation, User user) {
        this.id = id;
        this.recipientName = recipientName;
        this.contactNumber = contactNumber;
        this.deliveryLocation = deliveryLocation;
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address() {
    }
}
