package com.waitring.waitring.mapper;

import com.waitring.waitring.dto.keyword.KeywordInput;
import com.waitring.waitring.entity.Keyword;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KeywordMapper {

    KeywordMapper INSTANCE = Mappers.getMapper(KeywordMapper.class);

    Keyword keywordInputToKeyword(KeywordInput keywordInput);
}
