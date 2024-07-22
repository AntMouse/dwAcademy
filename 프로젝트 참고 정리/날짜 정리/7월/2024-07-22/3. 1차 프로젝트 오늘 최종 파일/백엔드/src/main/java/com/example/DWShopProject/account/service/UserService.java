package com.example.DWShopProject.account.service;

import com.example.DWShopProject.account.common.service.AccountService;
import com.example.DWShopProject.account.dto.LoginDTO;
import com.example.DWShopProject.account.dto.UserDTO;
import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.account.entity.User;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import com.example.DWShopProject.cart.repository.CartRepository;
import com.example.DWShopProject.account.repository.UserRepository;
import com.example.DWShopProject.common.security.enums.ApplicationRole;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserService implements AccountService<User, UserDTO> {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.cartRepository = cartRepository;
    }

    @Override
    public User createAccount(UserDTO userDto) {
        User user = User.builder()
                .accountId(userDto.getAccountId())
                .password(userDto.getPassword()) // 공용 회원가입에서 인코딩 되었음
                .email(userDto.getEmail())
                .accountRole(ApplicationRole.USER)

                .username(userDto.getUsername())
                .gender(userDto.getGender())
                .birthdate(userDto.getBirthdate())
                .contact(userDto.getContact())
                .isMarketingOptIn(userDto.isMarketingOptIn())
                .build();

        return userRepository.save(user); // Save the user entity to the repository
    }

    @Override
    public UserDTO saveAccount(UserDTO userDto) {
        User user = createAccount(userDto); // Create the User entity

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        return new UserDTO(user); // Map the User entity to UserDto and return it
    }

    @Override
    public UserDTO updateAccount(Long id, UserDTO userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 엔티티의 업데이트 메서드를 사용하여 정보 변경
        existingUser.updateUserProfile(
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getGender(),
                userDto.getBirthdate(),
                userDto.getContact()
        );

        if (userDto.getAccountRole() != null) {
            existingUser.updateAccountRoles(userDto.getAccountRole());
        }

        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    @Override
    public UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .accountRole(user.getAccountRole())
                .accountId(user.getAccountId())
                .username(user.getUsername())
                .password(null) // 비밀번호 필드를 null로 설정
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .email(user.getEmail())
                .contact(user.getContact())
                .isActive(user.isActive())
                .isMarketingOptIn(user.isMarketingOptIn())
                .registrationDate(user.getRegistrationDate())
                .build();
    }

    @Override
    public void deleteAccount(Long id) {
        userRepository.deleteById(id);
    }





    // 어드민 생성 (임시)
    public User createAdmin(UserDTO userDto) {
        if (userRepository.findByAccountId(userDto.getAccountId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다: " + userDto.getAccountId());
        }

        User user = User.builder()
                .accountId(userDto.getAccountId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .accountRole(ApplicationRole.SUPER_ADMIN) // ADMIN으로 설정
                .username(userDto.getUsername())
                .gender(userDto.getGender())
                .birthdate(userDto.getBirthdate())
                .contact(userDto.getContact())
                .isMarketingOptIn(userDto.isMarketingOptIn())
                .build();

        userRepository.save(user);

        return user;
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // mypage 수정 완료
    public User edit(UserDTO userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.updateUserProfile(userDto.getEmail(), userDto.getUsername(), userDto.getGender(), userDto.getBirthdate(), userDto.getContact());

        return userRepository.save(existingUser);
    }

    // UserDTO를 User 객체로 변환하는 메서드
    public UserDTO AdminupdateUser(Long id, UserDTO userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 회원 타입 업데이트
        if (userDto.getAccountRole() != null) {
            existingUser.updateAccountRoles(userDto.getAccountRole());
        }

        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }

    /*로그인*/
    @Transactional
    public String login(LoginDTO loginDto, HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.findByAccountId(loginDto.getAccountId());

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("회원이 존재하지 않음");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 토큰 생성
        String token = jwtUtil.createToken(user.getAccountId(), user.getAccountRole());
        return token; // 토큰 반환
    }

    // 회원가입 이메일 찾기 메소드
    public Optional<User> findUserByAccountIdAndEmail(UserDTO userDto) {
        return userRepository.findByAccountIdAndEmail(userDto.getAccountId(), userDto.getEmail());
    }

    public List<User> findUsersByEmail(UserDTO userDto) {
        return userRepository.findAllByEmail(userDto.getEmail());
    }

    public void passwordResetByEmail(User user, int verificationCode) {
        user.updatePassword(passwordEncoder.encode(String.valueOf(verificationCode)));
        userRepository.save(user);
    }
}
