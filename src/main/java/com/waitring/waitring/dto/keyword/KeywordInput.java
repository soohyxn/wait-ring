package com.waitring.waitring.dto.keyword;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(description = "입력받은 키워드 정보")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordInput {

    @Schema(description = "키워드명")
    @NotBlank
    private String name;
}
