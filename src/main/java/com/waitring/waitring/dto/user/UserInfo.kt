package com.waitring.waitring.dto.user

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원 정보")
data class UserInfo (
        @field:Schema(description = "회원Id")
        val id: Long,

        @field:Schema(description = "이메일")
        val email: String,

        @field:Schema(description = "닉네임")
        val nickname: String,

        @field:Schema(description = "포인트 점수")
        val point: Int,
)