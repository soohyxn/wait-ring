package com.waitring.waitring.controller

import com.google.gson.Gson
import com.waitring.waitring.dto.keyword.KeywordInput
import com.waitring.waitring.service.KeywordService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@WebMvcTest(KeywordController::class)
@MockBean(JpaMetamodelMappingContext::class)
@AutoConfigureMockMvc(addFilters = false)
internal class KeywordControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var keywordService: KeywordService

    /**
     * 테스트 데이터 생성
     */
    private fun generateKeywordInput() = KeywordInput(name = "양식")

    @Test
    @DisplayName("키워드 등록")
    fun addKeyword() {
        // given
        val keywordInput = generateKeywordInput()

        // when
        val result = mockMvc.post("/keyword") {
            contentType = APPLICATION_JSON
            content = Gson().toJson(keywordInput)
        }

        // then
        result.andExpect {
            status { isCreated() }
            jsonPath("$.result").value("키워드 등록이 완료되었습니다.")
        }
    }
}