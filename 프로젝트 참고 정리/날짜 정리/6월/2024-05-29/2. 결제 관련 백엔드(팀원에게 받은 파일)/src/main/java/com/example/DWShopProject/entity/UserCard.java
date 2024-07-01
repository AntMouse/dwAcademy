package com.example.DWShopProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cardnumber;
    String cardpassword;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }


}
