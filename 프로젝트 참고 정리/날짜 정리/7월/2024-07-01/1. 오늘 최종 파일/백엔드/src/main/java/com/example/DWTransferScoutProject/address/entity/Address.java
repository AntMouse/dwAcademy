package com.example.DWTransferScoutProject.address.entity;

import com.example.DWTransferScoutProject.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
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

    public Long getId() {
        return id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public User getUser() {
        return user;
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
