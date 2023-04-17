package com.waitring.waitring.controller;

import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.dto.user.UserInfo;
import com.waitring.waitring.dto.user.UserInput;
import com.waitring.waitring.dto.user.UserLogin;
import com.waitring.waitring.entity.User;
import com.waitring.waitring.exception.ErrorResponse;
import com.waitring.waitring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Tag(name = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param userInput 입력받은 회원 정보
     */
    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = StringResponse.class))),
            @ApiResponse(responseCode = "400", description = "유효성 검증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody UserInput userInput) {
        userService.addUser(userInput);
        return ResponseEntity.status(CREATED).body(new StringResponse("회원가입이 완료되었습니다."));
    }

    /**
     * 로그인
     * @param userLogin 입력받은 회원 정보
     */
    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "로그인 성공", content = @Content(schema = @Schema(implementation = StringResponse.class))),
            @ApiResponse(responseCode = "400", description = "유효성 검증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "로그인 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserLogin userLogin) {
        userService.login(userLogin);
        return ResponseEntity.status(OK).body(new StringResponse("로그인이 완료되었습니다."));
    }

    /**
     * 회원조회
     * @param id 회원Id
     */
    @Operation(summary = "회원 조회", description = "회원 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = UserInfo.class))),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity getUser(@Parameter(description = "회원Id") @PathVariable("id") Long id) {
        UserInfo userInfo = userService.getUser(id);
        return ResponseEntity.status(OK).body(userInfo);
    }
}
