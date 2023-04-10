package com.waitring.waitring.dto.keyword

import io.swagger.v3.oas.annotations.media.Schema
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Schema(description = "키워드 정보")
data class KeywordInfo (
    @field:Schema(description = "키워드Id")
    private val id: Long,

    @field:Schema(description = "키워드명")
    private val name: String,
)