package com.example.DWTransferScoutProject.user.controller;

import com.example.DWTransferScoutProject.common.account.entity.BaseAccount;
import com.example.DWTransferScoutProject.user.dto.UserDto;
import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.user.service.UserService;
import com.example.DWTransferScoutProject.auth.security.JwtUtil;
import com.example.DWTransferScoutProject.common.exception.ResourceNotFoundException;
import com.example.DWTransferScoutProject.auth.security.AccountDetailsImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        Optional<User> optionalUser = userService.findByUsername(userDto.getUsername());
        List<User> users = userService.findUsersByEmail(userDto.getEmail());

        try {
            if (optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else if (users.size() > 3) { // EmailController().MAX_EMAIL_ASSOCIATED_ACCOUNTS 값을 직접 지정하거나 상수로 정의
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("하나의 이메일에 3개의 아이디만 만들 수 있습니다.");
            }

            User user = userService.signUp(userDto);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(userService::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return ResponseEntity.ok(userService.mapToDTO(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        log.info("컨트롤러 DTO:{}", userDto);

        UserDto user = userService.updateAccount(id, userDto);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteAccount(id);
            return ResponseEntity.ok("사용자가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("사용자 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    @GetMapping("/mypage")
    public ResponseEntity<UserDto> getMyPage(@AuthenticationPrincipal AccountDetailsImpl userDetails) {
        BaseAccount account = userDetails.getAccount();
        if (account instanceof User) {
            User user = (User) account;
            UserDto myPageDTO = userService.mapToDTO(user);
            return ResponseEntity.ok(myPageDTO);
        } else {
            return ResponseEntity.status(403).build(); // 권한 없음 상태 반환
        }
    }

    // mypage 회원탈퇴 API 및 header 쿠키삭제
    @DeleteMapping("/mypage")
    public ResponseEntity<String> deleteMyUser(@AuthenticationPrincipal AccountDetailsImpl userDetails,
                                               @CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                                               HttpServletResponse response) {
        userService.deleteAccount(userDetails.getAccount().getId());
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
        return ResponseEntity.ok("사용자 계정이 성공적으로 삭제되었습니다.");
    }

    @PutMapping("/mypage")
    public ResponseEntity<UserDto> updateMyUser(@RequestBody UserDto userDto,
                                                @AuthenticationPrincipal AccountDetailsImpl userDetails,
                                                @CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                                                HttpServletResponse response) {
        BaseAccount account = userDetails.getAccount();
        if (account instanceof User) {
            User currentUser = (User) account;
            if (!currentUser.getId().equals(userDto.getId())) {
                return ResponseEntity.status(403).build();
            }

            UserDto user = userService.edit(userDto);
            jwtCookie.setValue(null);
            jwtCookie.setMaxAge(0);
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(403).build(); // 권한 없음 상태 반환
        }
    }
}
