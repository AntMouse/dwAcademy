package com.example.DWShopProject.user.controller;
import com.example.DWShopProject.common.exception.ResourceNotFoundException;
import com.example.DWShopProject.user.dto.LoginDTO;
import com.example.DWShopProject.user.dto.UserDTO;
import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import com.example.DWShopProject.user.repository.UserRepository;
import com.example.DWShopProject.common.security.userdetails.UserDetailsImpl;
import com.example.DWShopProject.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/type")
    public ResponseEntity<?> getUserType(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        String userType = jwtUtil.getUserTypeFromToken(token);
        System.out.println("User Type from Token: " + userType); // 클레임에서 가져온 멤버 타입 로그

        return ResponseEntity.ok(Collections.singletonMap("userType", userType)); // userType으로 반환
    }

    @PostMapping("/idcheck")
    public ResponseEntity<?> IdCheck(@RequestBody UserDTO userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        System.out.println(userDto.getUsername());
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } return ResponseEntity.ok().build();
    }

//    @GetMapping("/users")
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        List<UserDTO> userDTOS = users.stream()
//                .map(userService::mapToDTO)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(userDTOS);
//    }
//
//    @GetMapping("/us    ers/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
//        return ResponseEntity.ok(userService.mapToDTO(user));
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDto) {
//        System.out.println("업데이트컨트롤러실행");
//        log.info("컨트롤러 DTO:{}", userDto);
//
//        UserDTO user = userService.AdminupdateUser(id, userDto);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // 관리자 전용 회원 삭제 API
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//        try {
//            userService.deleteUser(id);
//            return ResponseEntity.ok("사용자가 성공적으로 삭제되었습니다.");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("사용자 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
//        }
//    }
//    /* 마이페이지 조회 API */
//    @GetMapping("/mypage")
//    public ResponseEntity<UserDTO> getMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        User user = userDetails.getUser(); // UserDetailsImpl 객체에서 User 정보 추출
//        UserDTO myPageDTO = new UserDTO(user);
//        return ResponseEntity.ok(myPageDTO);
//    }
//
//    // mypage 회원탈퇴 API 및 header 쿠키삭제
//    @DeleteMapping("/mypage")
//    public ResponseEntity<String> deleteMyUser(@AuthenticationPrincipal UserDetailsImpl userDetails,
//                                               @CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
//                                               HttpServletResponse response) {
//        userService.deleteUser(userDetails.getUser().getId());
//        jwtCookie.setValue(null);
//        jwtCookie.setMaxAge(0);
//        jwtCookie.setPath("/");
//        response.addCookie(jwtCookie);
//        return ResponseEntity.ok("사용자 계정이 성공적으로 삭제되었습니다.");
//    }
//
//    // mypage 회원 수정 API
//    @PutMapping("/mypage")
//    public ResponseEntity<User> updateMyUser(@RequestBody UserDTO userDto,
//                                             @AuthenticationPrincipal UserDetailsImpl userDetails,
//                                             @CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
//                                             HttpServletResponse response) {
//        User currentUser = userDetails.getUser();
//        if (!currentUser.getId().equals(userDto.getId())) {
//            return ResponseEntity.status(403).build();
//        }
//
//        User user = userService.edit(userDto);
//        jwtCookie.setValue(null);
//        jwtCookie.setMaxAge(0);
//        jwtCookie.setPath("/");
//        response.addCookie(jwtCookie);
//
//        return ResponseEntity.ok(user);
//    }
//
//    /*로그인 API*/
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginDTO loginDao, HttpServletResponse response) {
//        try {
//            String token = userService.login(loginDao, response); // 로그인 서비스에서 토큰 생성 및 반환
//            return ResponseEntity.ok(token); // 클라이언트에게 토큰을 반환
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("로그인 중 오류가 발생했습니다. 다시 시도해주세요.");
//        }
//    }

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
