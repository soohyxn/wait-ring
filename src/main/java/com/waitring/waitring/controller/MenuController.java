package com.waitring.waitring.controller;

import com.waitring.waitring.dto.menu.MenuInfo;
import com.waitring.waitring.dto.menu.MenuInput;
import com.waitring.waitring.exception.ErrorResponse;
import com.waitring.waitring.service.MenuService;
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

@Tag(name = "메뉴 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     * 메뉴 등록
     * @param id 메뉴가 속한 가게Id
     * @param menuInput 입력받은 메뉴 정보
     */
    @Operation(summary = "메뉴 등록", description = "메뉴를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공", content = @Content(schema = @Schema(implementation = StringResponse.class))),
            @ApiResponse(responseCode = "400", description = "유효성 검증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "가게 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{id}")
    public ResponseEntity addStore(@Parameter(description = "가게Id") @PathVariable("id") Long id,
                                   @Valid @RequestBody MenuInput menuInput) {
        menuService.addMenu(id, menuInput);
        return ResponseEntity.status(CREATED).body(new StringResponse("메뉴 등록이 완료되었습니다."));
    }
}
