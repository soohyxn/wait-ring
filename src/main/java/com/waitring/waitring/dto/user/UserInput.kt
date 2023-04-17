package com.waitring.waitring.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Schema(description = "입력받은 회원 정보")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

    @Schema(description = "이메일")
    @NotBlank
    private String email;

    @Schema(description = "비밀번호")
    @NotBlank
    private String password;

    @Schema(description = "닉네임")
    @NotBlank
    private String nickname;

    @Schema(description = "포인트 점수")
    private Integer point;
}
