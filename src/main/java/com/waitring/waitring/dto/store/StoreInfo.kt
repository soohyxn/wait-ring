package com.waitring.waitring.dto.store

import com.waitring.waitring.dto.keyword.KeywordInfo
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

@Schema(description = "가게 정보")
data class StoreInfo(
        @field:Schema(description = "가게Id")
        val id: Long,

        @field:Schema(description = "가게명")
        @field:NotBlank
        val name: String,

        @field:Schema(description = "가게 동네 위치")
        @field:NotBlank
        val areaDong: String,

        @field:Schema(description = "가게 이미지")
        val image: String,

        @field:Schema(description = "웨이팅 가능 여부")
        val waitingFlag: Boolean,

        @field:Schema(description = "예약 가능 여부")
        val reserveFlag: Boolean,

        @field:Schema(description = "가게 키워드 리스트")
        val keywords: List<KeywordInfo>,
)
