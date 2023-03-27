package com.waitring.waitring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.waitring.waitring.dto.keyword.KeywordInput;
import com.waitring.waitring.service.KeywordService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KeywordController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class KeywordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeywordService keywordService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 테스트 데이터 생성
     */
    KeywordInput generateKeywordInput() {
        return KeywordInput.builder()
                .name("양식")
                .build();
    }

    @Test
    @DisplayName("키워드 등록")
    void addKeyword() throws Exception {
        // given
        KeywordInput keywordInput = generateKeywordInput();

        // when
        ResultActions result = mockMvc.perform(post("/keyword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(keywordInput)));

        // then
        result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").value("키워드 등록이 완료되었습니다."));
    }
}