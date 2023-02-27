package com.waitring.waitring.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "가게 정보")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreInfo {

    @Schema(description = "가게Id")
    @NotNull
    private Long id;

    @Schema(description = "가게명")
    @NotBlank
    private String name;

    @Schema(description = "가게 동네 위치")
    @NotBlank
    private String areaDong;

    @Schema(description = "가게 키워드")
    private String keyword;

    @Schema(description = "가게 이미지")
    private String image;

    @Schema(description = "웨이팅 가능 여부")
    @NotNull
    private Boolean waitingFlag;

    @Schema(description = "예약 가능 여부")
    @NotNull
    private Boolean reserveFlag;
}
