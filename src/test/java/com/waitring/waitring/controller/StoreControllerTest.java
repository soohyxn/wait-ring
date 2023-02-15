package com.waitring.waitring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.service.StoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
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
     * 가게 테스트 데이터 생성
     */
    StoreDetailInfo generateStoreDetailInfo() {
        return StoreDetailInfo.builder()
                .name("고든램지 버거")
                .areaDong("신천동")
                .areaDetail("서울 송파구 올림픽로 300 롯데월드몰 B1층")
                .keyword("프리미엄, 양식, 버거")
                .imageUrl("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max")
                .openTime("10:00")
                .closeTime("20:30")
                .closeDay("매주 셋째주 월요일")
                .waitingFlag(true)
                .reserveFlag(false)
                .build();
    }

    @Test
    @DisplayName("가게 상세 정보 조회")
    void getStoreDetailInfo() throws Exception {
        // given
        StoreDetailInfo storeDetailInfo = generateStoreDetailInfo();
        given(storeService.getStoreInfoDetail(any())).willReturn(storeDetailInfo);

        // when
        ResultActions result = mockMvc.perform(get("/stores/1"));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("고든램지 버거"))
                .andExpect(jsonPath("$.areaDong").value("신천동"))
                .andExpect(jsonPath("$.areaDetail").value("서울 송파구 올림픽로 300 롯데월드몰 B1층"))
                .andExpect(jsonPath("$.keyword").value("프리미엄, 양식, 버거"))
                .andExpect(jsonPath("$.openTime").value("10:00"))
                .andExpect(jsonPath("$.closeTime").value("20:30"))
                .andExpect(jsonPath("$.closeDay").value("매주 셋째주 월요일"));
    }
}