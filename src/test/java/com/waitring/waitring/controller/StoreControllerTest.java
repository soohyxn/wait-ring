package com.waitring.waitring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.waitring.waitring.dto.menu.MenuInfo;
import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.dto.store.StoreInfo;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.service.StoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StoreController.class)
@MockBean(JpaMetamodelMappingContext.class)
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 테스트 데이터 생성
     */
    StoreDetailInfo generateStoreDetailInfo() {
        return StoreDetailInfo.builder()
                .id(1L)
                .name("고든램지 버거")
                .areaDong("신천동")
                .areaDetail("서울 송파구 올림픽로 300 롯데월드몰 B1층")
                .images(new String[]{
                        "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max",
                        "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max",
                        "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-23.jpg?w=1600&cbr=1&q=90&fit=max"})
                .openTime("10:00")
                .closeTime("20:30")
                .closeDay("매주 셋째주 월요일")
                .waitingFlag(true)
                .reserveFlag(false)
                .menus(new ArrayList<>(Collections.singleton(generateMenuInfo())))
                .build();
    }

    StoreInfo generateStoreInfo() {
        return StoreInfo.builder()
                .id(1L)
                .name("고든램지 버거")
                .areaDong("신천동")
                .keyword("프리미엄, 양식, 버거")
                .image("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max")
                .waitingFlag(true)
                .reserveFlag(false)
                .build();
    }

    MenuInfo generateMenuInfo() {
        return MenuInfo.builder()
                .id(2L)
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
        StoreDetailInfo storeDetailInfo = generateStoreDetailInfo();

        // when
        ResultActions result = mockMvc.perform(post("/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(storeDetailInfo)));

        // then
        result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").value("가게 등록이 완료되었습니다."));
    }

    @Test
    @DisplayName("가게 상세 조회")
    void getStoreDetailInfo() throws Exception {
        // given
        StoreDetailInfo storeDetailInfo = generateStoreDetailInfo();
        given(storeService.getStoreDetail(1L)).willReturn(storeDetailInfo);

        // when
        ResultActions result = mockMvc.perform(get("/store/1"));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("고든램지 버거"))
                .andExpect(jsonPath("$.areaDong").value("신천동"))
                .andExpect(jsonPath("$.areaDetail").value("서울 송파구 올림픽로 300 롯데월드몰 B1층"))
                .andExpect(jsonPath("$.keyword").value("프리미엄, 양식, 버거"))
                .andExpect(jsonPath("$.images.length()").value(3))
                .andExpect(jsonPath("$.openTime").value("10:00"))
                .andExpect(jsonPath("$.closeTime").value("20:30"))
                .andExpect(jsonPath("$.closeDay").value("매주 셋째주 월요일"))
                .andExpect(jsonPath("$.waitingFlag").value(true))
                .andExpect(jsonPath("$.reserveFlag").value(false))
                .andExpect(jsonPath("$.menus.length()").value(1));
    }

    @Test
    @DisplayName("검색어로 가게 조회")
    void getStoreListByWord() throws Exception {
        // given
        List<StoreInfo> storeInfoList = Collections.singletonList(generateStoreInfo());
        given(storeService.getStoreListByWord("버거")).willReturn(storeInfoList);

        // when
        ResultActions result = mockMvc.perform(get("/store")
                .param("query", "버거"));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("고든램지 버거"))
                .andExpect(jsonPath("$[0].areaDong").value("신천동"))
                .andExpect(jsonPath("$[0].keyword").value("프리미엄, 양식, 버거"))
                .andExpect(jsonPath("$[0].image").isNotEmpty())
                .andExpect(jsonPath("$[0].waitingFlag").value(true))
                .andExpect(jsonPath("$[0].reserveFlag").value(false));
    }
}