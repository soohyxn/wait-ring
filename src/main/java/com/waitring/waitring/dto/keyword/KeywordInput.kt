package com.waitring.waitring.dto.keyword

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

@Schema(description = "입력받은 키워드 정보")
data class KeywordInput (
        @field:Schema(description = "키워드명")
        @field:NotBlank
        val name: String?,
)