package com.waitring.waitring.controller

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "응답 정보")
data class StringResponse (
        @field:Schema(description = "응답 메시지")
        val result: String,
)