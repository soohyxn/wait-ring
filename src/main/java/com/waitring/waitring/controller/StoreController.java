package com.waitring.waitring.controller;

import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "가게 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 상세 정보 조회 API
     * @param id 가게Id
     * @return 가게 상세 정보
     */
    @Operation(summary = "가게 상세 정보 조회", description = "가게 상세 정보 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = StoreDetailInfo.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "가게Id", in = ParameterIn.PATH),
    })
    @GetMapping("/{id}")
    public ResponseEntity getStoreDetailInfo(@PathVariable("id") Long id) {
        StoreDetailInfo store = storeService.getStoreInfoDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }
}
