package com.example.DWTransferScoutProject.config;

import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import com.example.DWTransferScoutProject.superadmin.dto.SuperAdminDto;
import com.example.DWTransferScoutProject.superadmin.service.SuperAdminService;
import com.example.DWTransferScoutProject.user.dto.UserDto;
import com.example.DWTransferScoutProject.user.entity.GenderEnum;
import com.example.DWTransferScoutProject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class InitializerConfig {

    private final SuperAdminService superAdminService;
    private final UserService userService;

    @Bean
    public ApplicationRunner initializeAdmin() {
        return args -> {
            String defaultAdminId = "admin";
            String defaultAdminPassword = "1234";
            String defaultAdminEmail = "admin@example.com";

            if (superAdminService.findByAccountId(defaultAdminId).isEmpty()) {
                SuperAdminDto adminDto = SuperAdminDto.builder()
                        .superAdminId(defaultAdminId)
                        .password(defaultAdminPassword)
                        .email(defaultAdminEmail)
                        .accountType(ApplicationRoleEnum.SUPER_ADMIN)
                        .build();
                superAdminService.signUp(adminDto);
                System.out.println("Default admin account created.");
            } else {
                System.out.println("Default admin account already exists.");
            }
        };
    }

    @Bean
    public ApplicationRunner initializeUsers() {
        return args -> {
            for (int i = 1; i <= 10; i++) {
                String userId = "user" + i;
                if (userService.findByAccountId(userId).isEmpty()) {
                    UserDto userDto = UserDto.builder()
                            .userId(userId)
                            .password("1234")
                            .confirmPassword("1234")
                            .username("User " + i)
                            .email("user" + i + "@example.com")
                            .accountType(ApplicationRoleEnum.USER)
                            .birthdate(LocalDate.of(1990, 1, 1).toString()) // 예시: 1990년 1월 1일 생
                            .gender(GenderEnum.MALE) // 예시: 남성
                            .contact("010-1234-567" + i) // 예시: 010-1234-5671, 010-1234-5672, ...
                            .build();
                    userService.signUp(userDto);
                    System.out.println("User account created: " + userId);
                } else {
                    System.out.println("User account already exists: " + userId);
                }
            }
        };
    }
}
