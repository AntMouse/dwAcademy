package com.example.DWShopProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    private String usertype;
    private String userId;
    private String username;
    private String password;
    private String birthdate;
    private String gender;
    private String email;
    private String contact;
    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCard> cards = new ArrayList<>();

    public void addCard(UserCard card) {
        cards.add(card);
        card.setUser(this);
    }

    public void removeCard(UserCard card) {
        cards.remove(card);
        card.setUser(null);
    }

}
