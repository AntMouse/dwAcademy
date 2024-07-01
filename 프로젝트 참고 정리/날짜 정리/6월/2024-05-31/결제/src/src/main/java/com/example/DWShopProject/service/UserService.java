package com.example.DWShopProject.service;

import com.example.shoppingmall.dto.SignUpDAO;
import com.example.DWShopProject.entity.User;
import com.example.DWShopProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public void signUp(SignUpDAO signUpDAO) {
        Long id = null;
        String usertype = signUpDAO.getUsertype();
        String userId = signUpDAO.getUserId();;
        String username = signUpDAO.getUsername();
        String password = signUpDAO.getPassword();
        String birthdate = signUpDAO.getBirthdate();
        String gender = signUpDAO.getGender();
        String email = signUpDAO.getEmail();
        String contact = signUpDAO.getContact();
        String address = signUpDAO.getAddress();

        if (userRepository.findByUserId(userId).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원 이름입니다: " + userId);
        }

        User user = new User(id, usertype, userId, username, password, birthdate, gender, email, contact, address, null);
        userRepository.save(user);

    }

    //sb-ib82i30988461@personal.example.com
    //gusqls12
    // 유저 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 유저 수정
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    // 수정 완료
    public User edit(User user) {
        return userRepository.save(user);
    }

}
