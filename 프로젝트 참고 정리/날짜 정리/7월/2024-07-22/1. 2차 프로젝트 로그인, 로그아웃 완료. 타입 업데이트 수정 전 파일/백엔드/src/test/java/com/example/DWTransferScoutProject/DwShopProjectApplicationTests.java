package com.example.DWTransferScoutProject;

import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import com.example.DWTransferScoutProject.superadmin.service.SuperAdminService;
import com.example.DWTransferScoutProject.user.dto.UserDto;
import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.user.repository.UserRepository;
import com.example.DWTransferScoutProject.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DwShopProjectApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SuperAdminService superAdminService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	void testUserTypeChange() {
		// 1. 일반 user 회원가입
		UserDto userDto = UserDto.builder()
				.userId("testuser")
				.username("Test User")
				.password("password")
				.confirmPassword("password")
				.email("testuser@example.com")
				.accountType(ApplicationRoleEnum.USER)
				.build();
		User user = userService.signUp(userDto);

		// 2. 회원가입된 유저 확인
		User createdUser = userRepository.findByAccountId("testuser").orElse(null);
		assertThat(createdUser).isNotNull();
		assertThat(createdUser.getAccountType()).isEqualTo(ApplicationRoleEnum.USER);

		// 3. 유저 타입을 super_admin으로 변경
		UserDto updatedUserDto = superAdminService.updateUserType(createdUser.getId(), ApplicationRoleEnum.SUPER_ADMIN);

		// 4. 유저 타입 변경 확인
		User updatedUser = userRepository.findByAccountId("testuser").orElse(null);
		assertThat(updatedUser).isNotNull();
		assertThat(updatedUser.getAccountType()).isEqualTo(ApplicationRoleEnum.SUPER_ADMIN);
	}
}
