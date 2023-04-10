package com.waitring.waitring.service

import com.waitring.waitring.repository.KeywordRepository
import com.waitring.waitring.mapper.KeywordMapper
import com.waitring.waitring.dto.keyword.KeywordInput
import com.waitring.waitring.entity.Keyword
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class KeywordService(private val keywordRepository: KeywordRepository) {

    companion object {
        private val log = LoggerFactory.getLogger(KeywordService::class.java)
        private val mapper = Mappers.getMapper(KeywordMapper::class.java)
    }

    /**
     * 키워드 등록
     * @param keywordInput 입력받은 키워드 정보
     */
    fun addKeyword(keywordInput: KeywordInput): Keyword {
        val keyword = mapper.keywordInputToKeyword(keywordInput)
        val saveKeyword = keywordRepository.save(keyword)
        log.info("saveKeyword: $saveKeyword")
        return saveKeyword
    }
}