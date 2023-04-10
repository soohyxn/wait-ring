package com.waitring.waitring.service;

import com.waitring.waitring.dto.keyword.KeywordInput;
import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.mapper.KeywordMapper;
import com.waitring.waitring.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KeywordService {

    private final KeywordRepository keywordRepository;
    private final KeywordMapper keywordMapper;

    /**
     * 키워드 등록
     * @param keywordInput 입력받은 키워드 정보
     */
    public Keyword addKeyword(KeywordInput keywordInput) {
        Keyword keyword = keywordMapper.INSTANCE.keywordInputToKeyword(keywordInput);
        Keyword saveKeyword = keywordRepository.save(keyword);
        log.info("saveKeyword: " + saveKeyword);
        return saveKeyword;
    }
}