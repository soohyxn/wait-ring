package com.waitring.waitring.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "응답 정보")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StringResponse {

    @Schema(description = "응답 메시지")
    private String result;
}
