package com.waitring.waitring.dto.menu

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Schema(description = "입력받은 메뉴 정보")
data class MenuInput(
        @field:Schema(description = "메뉴명")
        @field:NotBlank
        val name: String?,

        @field:Schema(description = "메뉴 가격")
        @field:NotNull
        val price: Int?,

        @field:Schema(description = "메뉴 설명")
        val detail: String?,

        @field:Schema(description = "메뉴 이미지")
        val image: String?,
)
