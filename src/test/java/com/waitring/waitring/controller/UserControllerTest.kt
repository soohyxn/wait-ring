package com.waitring.waitring.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.waitring.waitring.dto.user.UserInfo
import com.waitring.waitring.dto.user.UserInput
import com.waitring.waitring.dto.user.UserLogin
import com.waitring.waitring.service.UserService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
@MockBean(JpaMetamodelMappingContext::class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    /**
     * 테스트 데이터 생성
     */
    private fun generateUserInput() = UserInput(
            email = "user1@waitring.com",
            password = BCryptPasswordEncoder().encode("1234"),
            nickname = "유저1",
            point = 1234
    )

    private fun generateUserLogin() = UserLogin(
            email = "user1@waitring.com",
            password = "1234"
    )

    private fun generateUserInfo() = UserInfo(
            id = 1L,
            email = "user1@waitring.com",
            nickname = "유저1",
            point = 1234
    )

    @Test
    @DisplayName("회원가입")
    fun addUser() {
        // given
        val userInput = generateUserInput()

        // when
        val result = mockMvc.perform(post("/user")
                .contentType(APPLICATION_JSON)
                .content(Gson().toJson(userInput)))

        // then
        result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").value("회원가입이 완료되었습니다."))
    }

    @Test
    @DisplayName("로그인")
    fun login() {
        // given
        val userLogin = generateUserLogin()

        // when
        val result = mockMvc.perform(post("/user/login")
                .contentType(APPLICATION_JSON)
                .content(Gson().toJson(userLogin)))

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("로그인이 완료되었습니다."))
    }

    @Test
    @DisplayName("회원 조회")
    fun getUser() {
        // given
        val userInfo = generateUserInfo()
        given(userService.getUser(any(Long::class.java))).willReturn(userInfo)

        // when
        val result = mockMvc.perform(get("/user/1"))

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("user1@waitring.com"))
                .andExpect(jsonPath("$.nickname").value("유저1"))
                .andExpect(jsonPath("$.point").value(1234))
    }
}