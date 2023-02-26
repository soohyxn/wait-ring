package com.waitring.waitring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.waitring.waitring.dto.menu.MenuInput;
import com.waitring.waitring.service.MenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuController.class)
@MockBean(JpaMetamodelMappingContext.class)
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 테스트 데이터 생성
     */
    MenuInput generateMenuInput() {
        return MenuInput.builder()
                .name("헬스 키친 버거")
                .price(31000)
                .detail("모차렐라 치즈, 로스티드 할라피뇨&토마토, 아보카도, 할리피뇨 아이뮬리")
                .image("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max")
                .build();
    }

    @Test
    @DisplayName("가게 등록")
    void addStore() throws Exception {
        // given
        MenuInput menuInput = generateMenuInput();

        // when
        ResultActions result = mockMvc.perform(post("/menus/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(menuInput)));

        // then
        result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").value("메뉴 등록이 완료되었습니다."));
    }
}