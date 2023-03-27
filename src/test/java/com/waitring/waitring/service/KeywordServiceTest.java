package com.waitring.waitring.service;

import com.waitring.waitring.dto.keyword.KeywordInput;
import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.repository.KeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KeywordServiceTest {

    @Mock
    private KeywordRepository keywordRepository;

    @InjectMocks
    private KeywordService keywordService;

    /**
     * 테스트 데이터 생성
     */
    Keyword generateKeyword() {
        return Keyword.builder()
                .id(1L)
                .name("양식")
                .build();
    }

    KeywordInput generateKeywordInput() {
        return KeywordInput.builder()
                .name("양식")
                .build();
    }

    @Test
    @DisplayName("키워드 등록")
    void addKeyword() {
        // given
        Keyword keyword = generateKeyword();
        given(keywordRepository.save(any(Keyword.class))).willReturn(keyword);

        // when
        Keyword addKeyword = keywordService.addKeyword(generateKeywordInput());

        // then
        assertNotNull(addKeyword);
        assertThat(addKeyword.getId()).isEqualTo(1L);

        // verify
        verify(keywordRepository).save(any(Keyword.class));
    }
}