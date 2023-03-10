package com.waitring.waitring.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "회원 정보")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Schema(description = "회원Id")
    @NotNull
    private Long id;

    @Schema(description = "이메일")
    @NotBlank
    private String email;

    @Schema(description = "닉네임")
    @NotBlank
    private String nickname;

    @Schema(description = "포인트 점수")
    private Integer point;
}
