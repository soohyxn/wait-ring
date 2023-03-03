package com.waitring.waitring.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(description = "입력받은 회원 정보")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    @Schema(description = "이메일")
    @NotBlank
    private String email;

    @Schema(description = "비밀번호")
    @NotBlank
    private String password;
}
