package com.waitring.waitring.dto.menu

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

@Schema(description = "메뉴 정보")
data class MenuInfo(
        @field:Schema(description = "메뉴Id")
        val id: Long,

        @field:Schema(description = "메뉴명")
        @field:NotBlank
        val name: String,

        @field:Schema(description = "메뉴 가격")
        val price: Int,

        @field:Schema(description = "메뉴 설명")
        val detail: String,

        @Schema(description = "메뉴 이미지")
        val image: String,
)
