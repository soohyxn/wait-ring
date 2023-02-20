package com.waitring.waitring.controller;

import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.dto.store.StoreInfo;
import com.waitring.waitring.exception.ErrorResponse;
import com.waitring.waitring.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Tag(name = "가게 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 등록
     * @param storeDetailInfo 입력받은 가게 정보
     */
    @Operation(summary = "가게 등록", description = "가게를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공", content = @Content(schema = @Schema(implementation = StringResponse.class))),
            @ApiResponse(responseCode = "400", description = "유효성 검증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addStore(@Valid @RequestBody StoreDetailInfo storeDetailInfo) {
        storeService.addStore(storeDetailInfo);
        return ResponseEntity.status(CREATED).body(new StringResponse("가게 등록이 완료되었습니다."));
    }

    /**
     * 가게 상세 조회
     * @param id 가게Id
     * @return 가게 상세 정보
     */
    @Operation(summary = "가게 상세 조회", description = "가게 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = StoreDetailInfo.class))),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity getStoreDetailInfo(@Parameter(description = "가게Id") @PathVariable("id") Long id) {
        StoreDetailInfo store = storeService.getStoreDetail(id);
        return ResponseEntity.status(OK).body(store);
    }

    /**
     * 검색어로 가게 조회
     * @param query 검색어
     * @return 검색된 가게 정보 리스트
     */
    @Operation(summary = "검색어로 가게 조회", description = "검색어로 가게를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = StoreDetailInfo.class))),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/")
    public ResponseEntity getStoreListByWord(@Parameter(description = "검색어")@RequestParam String query) {
        List<StoreInfo> stores = storeService.getStoreListByWord(query);
        return ResponseEntity.status(OK).body(stores);
    }
}
