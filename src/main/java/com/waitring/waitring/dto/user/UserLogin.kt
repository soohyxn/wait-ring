package com.waitring.waitring.dto.user

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

@Schema(description = "입력받은 회원 정보")
data class UserLogin (
        @field:Schema(description = "이메일")
        @field:NotBlank
        val email: String?,

        @field:Schema(description = "비밀번호")
        @field:NotBlank
        val password: String?,
)