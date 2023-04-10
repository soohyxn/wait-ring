package com.waitring.waitring.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import com.waitring.waitring.service.KeywordService
import com.waitring.waitring.exception.ErrorResponse
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid
import com.waitring.waitring.dto.keyword.KeywordInput
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "키워드 API")
@RestController
@RequestMapping("/keyword")
class KeywordController(private val keywordService: KeywordService) {
    /**
     * 키워드 등록
     * @param keywordInput 입력받은 키워드 정보
     */
    @Operation(summary = "키워드 등록", description = "키워드를 등록합니다.")
    @ApiResponses(
            ApiResponse(responseCode = "201", description = "등록 성공", content = [Content(schema = Schema(implementation = StringResponse::class))]),
            ApiResponse(responseCode = "400", description = "유효성 검증 실패", content = [Content(schema = Schema(implementation = ErrorResponse::class))])
    )
    @PostMapping
    fun addKeyword(@RequestBody @Valid keywordInput: KeywordInput ): ResponseEntity<*> {
        keywordService.addKeyword(keywordInput)
        return ResponseEntity.status(HttpStatus.CREATED).body(StringResponse("키워드 등록이 완료되었습니다."))
    }
}