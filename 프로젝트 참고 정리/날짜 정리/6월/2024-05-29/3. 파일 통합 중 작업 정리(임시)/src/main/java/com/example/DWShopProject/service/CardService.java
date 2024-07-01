package com.example.DWShopProject.service;

import com.example.DWShopProject.entity.User;
import com.example.DWShopProject.entity.UserCard;
import com.example.DWShopProject.repository.CardRepository;
import com.example.DWShopProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    CardRepository repository;
    @Autowired
    UserRepository userRepository;

    public User SaveCard(Long id,String cardnumber, String password) {


        //1. 카드 등록할 유저를 찾는다
        User target = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserCard newcard = new UserCard();
        //2. 해당 유저의 카드 정보를 입력 후 DTO로 받아와서 변환
        newcard.setCardnumber(cardnumber);
        newcard.setCardpassword(password);
        //3. 해당유저의 카드 정보 리스트에 추가
        target.addCard(newcard);
        //4. 저장하기
        return userRepository.save(target);


    }

}
