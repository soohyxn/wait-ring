package com.waitring.waitring.mapper

import com.waitring.waitring.dto.keyword.KeywordInput
import com.waitring.waitring.entity.Keyword
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface KeywordMapper {
    fun keywordInputToKeyword(keywordInput: KeywordInput): Keyword
}