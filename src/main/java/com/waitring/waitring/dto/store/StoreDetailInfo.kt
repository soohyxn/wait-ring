package com.waitring.waitring.dto.store

import com.waitring.waitring.dto.keyword.KeywordInfo
import com.waitring.waitring.dto.menu.MenuInfo
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

@Schema(description = "가게 상세 정보")
data class StoreDetailInfo(
        @field:Schema(description = "가게Id")
        val id: Long,

        @Schema(description = "가게명")
        @field:NotBlank
        val name: String,

        @field:Schema(description = "가게 동네 위치")
        @field:NotBlank
        val areaDong: String,

        @field:Schema(description = "가게 상세 주소")
        @field:NotBlank
        val areaDetail: String,

        @field:Schema(description = "가게 이미지")
        val images: List<String>,

        @field:Schema(description = "영업 오픈시간")
        @field:NotBlank
        val openTime: String,

        @field:Schema(description = "영업 마감시간")
        @field:NotBlank
        val closeTime: String,

        @field:Schema(description = "휴무일")
        val closeDay: String,

        @field:Schema(description = "웨이팅 가능 여부")
        val waitingFlag: Boolean,

        @field:Schema(description = "예약 가능 여부")
        val reserveFlag: Boolean,

        @field:Schema(description = "가게 메뉴 리스트")
        val menus: List<MenuInfo>,

        @field:Schema(description = "가게 키워드 리스트")
        val keywords: List<KeywordInfo>,
)

