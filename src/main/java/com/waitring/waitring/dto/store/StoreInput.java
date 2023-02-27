package com.waitring.waitring.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "입력받은 가게 정보")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreInput {

    @Schema(description = "가게명")
    @NotBlank
    private String name;

    @Schema(description = "가게 동네 위치")
    @NotBlank
    private String areaDong;

    @Schema(description = "가게 상세 주소")
    @NotBlank
    private String areaDetail;

    @Schema(description = "가게 키워드")
    private String keyword;

    @Schema(description = "가게 이미지")
    private String[] images;

    @Schema(description = "영업 오픈시간")
    @NotBlank
    private String openTime;

    @Schema(description = "영업 마감시간")
    @NotBlank
    private String closeTime;

    @Schema(description = "휴무일")
    private String closeDay;

    @Schema(description = "웨이팅 가능 여부")
    @NotNull
    private Boolean waitingFlag;

    @Schema(description = "예약 가능 여부")
    @NotNull
    private Boolean reserveFlag;
}
