package com.waitring.waitring.dto.user

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

@Schema(description = "입력받은 회원 정보")
data class UserInput(
        @field:Schema(description = "이메일")
        @field:NotBlank
        val email: String?,

        @field:Schema(description = "비밀번호")
        @field:NotBlank
        val password: String?,

        @field:Schema(description = "닉네임")
        @field:NotBlank
        val nickname: String?,

        @field:Schema(description = "포인트 점수")
        val point: Int?,
)