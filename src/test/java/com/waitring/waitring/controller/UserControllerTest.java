package com.waitring.waitring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.waitring.waitring.dto.user.UserInfo;
import com.waitring.waitring.dto.user.UserInput;
import com.waitring.waitring.dto.user.UserLogin;
import com.waitring.waitring.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 테스트 데이터 생성
     */
    UserInput generateUserInput() {
        return UserInput.builder()
                .email("user1@waitring.com")
                .password("1234")
                .nickname("유저1")
                .build();
    }

    UserLogin generateUserLogin() {
        return UserLogin.builder()
                .email("user1@waitring.com")
                .password("1234")
                .build();
    }

    UserInfo generateUserInfo() {
        return UserInfo.builder()
                .id(1L)
                .email("user1@waitring.com")
                .nickname("유저1")
                .point(1234)
                .build();
    }

    @Test
    @DisplayName("회원가입")
    void addUser() throws Exception {
        // given
        UserInput userInput = generateUserInput();

        // when
        ResultActions result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(userInput)));

        // then
        result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").value("회원가입이 완료되었습니다."));
    }

    @Test
    @DisplayName("로그인")
    void login() throws Exception {
        // given
        UserLogin userLogin = generateUserLogin();

        // when
        ResultActions result = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(userLogin)));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("로그인이 완료되었습니다."));
    }

    @Test
    @DisplayName("회원 조회")
    void getUser() throws Exception {
        // given
        UserInfo userInfo = generateUserInfo();
        given(userService.getUser(1L)).willReturn(userInfo);

        // when
        ResultActions result = mockMvc.perform(get("/user/1"));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("user1@waitring.com"))
                .andExpect(jsonPath("$.nickname").value("유저1"))
                .andExpect(jsonPath("$.point").value(1234));
    }
}