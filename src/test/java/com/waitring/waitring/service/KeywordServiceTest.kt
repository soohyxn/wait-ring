package com.waitring.waitring.service

import com.waitring.waitring.dto.keyword.KeywordInput
import com.waitring.waitring.entity.Keyword
import com.waitring.waitring.repository.KeywordRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class KeywordServiceTest {

    private val keywordRepository: KeywordRepository = mockk(relaxed = true)
    private val keywordService = spyk(KeywordService(keywordRepository))

    /**
     * 테스트 데이터 생성
     */
    private fun generateKeyword() = Keyword(name = "양식")

    private fun generateKeywordInput() = KeywordInput(name = "양식")

    @Test
    @DisplayName("키워드 등록")
    fun addKeyword() {
        // given
        val keywordInput = generateKeywordInput()
        every { keywordRepository.save<Keyword>(any()) } returns generateKeyword()

        // when
        val addKeyword = keywordService.addKeyword(keywordInput)

        // then
        assertNotNull(addKeyword)

        // verify
        verify { keywordRepository.save<Keyword>(any()) }
    }
}