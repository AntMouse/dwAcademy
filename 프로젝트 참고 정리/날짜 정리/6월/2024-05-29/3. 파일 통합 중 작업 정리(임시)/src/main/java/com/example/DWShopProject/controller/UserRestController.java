package com.example.DWShopProject.controller;
import com.example.DWShopProject.dao.LoginDao;
import com.example.DWShopProject.dao.UserDao;
import com.example.DWShopProject.entity.User;
import com.example.DWShopProject.repository.UserRepository;
import com.example.DWShopProject.security.UserDetailsImpl;
import com.example.DWShopProject.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserRestController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /*회원가입 API*/
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDao userDao) {
        try {
            User user = userService.signUp(userDao);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    // 모든 사용자 뷰 API
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    /* 관리자 전용 회원 추가 API */
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody UserDao userDao) {
        User user = userService.signUp(userDao);
        return ResponseEntity.ok(user);

    }

    // 관리자 전용 회원 삭제 API
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("사용자가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("사용자 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    // 관리자 전용 회원 수정 API
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDao userDao) {
        User user = userService.updateUser(id, userDao);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* 마이페이지 조회 API */
    @GetMapping("/mypage")
    public ResponseEntity<User> getMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser(); // UserDetailsImpl 객체에서 User 정보 추출
        return ResponseEntity.ok(user);
    }

    // mypage 회원탈퇴 API 및 header 쿠키삭제
    @DeleteMapping("/mypage")
    public ResponseEntity<String> deleteMyUser(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                                               HttpServletResponse response) {
        userService.deleteUser(userDetails.getUser().getId());
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
        return ResponseEntity.ok("사용자 계정이 성공적으로 삭제되었습니다.");
    }

    // mypage 회원 수정 API
    @PutMapping("/mypage")
    public ResponseEntity<User> updateMyUser(@RequestBody UserDao userDao,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                                             HttpServletResponse response) {
        User currentUser = userDetails.getUser();
        if (!currentUser.getId().equals(userDao.getId())) {
            return ResponseEntity.status(403).build();
        }

        User user = userService.edit(userDao);
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return ResponseEntity.ok(user);
    }

    /*로그인 API*/
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDao loginDao, HttpServletResponse response) {
        try {
            userService.login(loginDao, response);
            return ResponseEntity.ok("로그인이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("로그인 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    /*로그아웃 API*/
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                                         HttpServletResponse response) {
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return ResponseEntity.ok("로그아웃이 성공적으로 완료되었습니다.");
    }
}
