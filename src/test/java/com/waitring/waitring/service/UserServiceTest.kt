package com.waitring.waitring.service

import com.waitring.waitring.dto.user.UserInput
import com.waitring.waitring.dto.user.UserLogin
import com.waitring.waitring.entity.User
import com.waitring.waitring.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class UserServiceTest {

    private val userRepository: UserRepository = mockk(relaxed = true)
    private val passwordEncoder: PasswordEncoder = mockk(relaxed = true)
    private val userService = spyk(UserService(userRepository, passwordEncoder))

    /**
     * 테스트 데이터 생성
     */
    private fun generateUser() = User(
            email = "user1@waitring.com",
            password = BCryptPasswordEncoder().encode("1234"),
            nickname = "유저1",
            point = 0
    )

    private fun generateUserInput() = UserInput(
            email = "user1@waitring.com",
            password = BCryptPasswordEncoder().encode("1234"),
            nickname = "유저1",
            point = 0
    )

    private fun generateUserLogin() = UserLogin(
            email = "user1@waitring.com",
            password = "1234"
    )

    @Test
    @DisplayName("회원가입")
    fun addStore() {
        // given
        val userInput = generateUserInput()
        every { userRepository.save<User>(any()) } returns generateUser()

        // when
        val addUser = userService.addUser(userInput)

        // then
        assertNotNull(addUser)

        // verify
        verify { userRepository.save<User>(any()) }
    }

    @Test
    @DisplayName("로그인")
    fun login() {
        // given
        val userLogin = generateUserLogin()
        every { userRepository.findByEmail(any()) } returns generateUser()
        every { passwordEncoder.matches(any(), any()) } returns true

        // when
        val loginUser = userService.login(userLogin)

        // then
        assertNotNull(loginUser)

        // verify
        verify { userRepository.findByEmail(any()) }
        verify { passwordEncoder.matches(any(), any()) }
    }

    @Test
    @DisplayName("회원 조회")
    fun getUser() {
        // given
        every { userRepository.findByIdOrNull(any()) } returns generateUser()

        // when
        val userInfo = userService.getUser(1L)

        // then
        assertNotNull(userInfo)

        // verify
        verify { userRepository.findByIdOrNull(any()) }
    }
}