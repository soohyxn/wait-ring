package com.waitring.waitring.service;

import com.waitring.waitring.dto.user.UserInput;
import com.waitring.waitring.entity.User;
import com.waitring.waitring.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    /**
     * 테스트 데이터 생성
     */
    User generateUser() {
        return User.builder()
                .email("user1@waitring.com")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .nickname("유저1")
                .point(0)
                .build();
    }

    UserInput generateUserInput() {
        return UserInput.builder()
                .email("user1@waitring.com")
                .password("1234")
                .nickname("유저1")
                .build();
    }

    @Test
    @DisplayName("회원가입")
    void addStore() {
        // given
        User user = generateUser();
        given(userRepository.save(any(User.class))).willReturn(user);

        // when
        User addUser = userService.addUser(generateUserInput());

        // then
        assertNotNull(addUser);

        // verify
        verify(userRepository).save(any(User.class));
    }
}