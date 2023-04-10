package com.waitring.waitring.exception

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.validation.FieldError
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "에러 정보")
class ErrorResponse(code: String, message: String?, errors: List<ValidationError>? = null) {

    @field:Schema(description = "응답코드")
    var code = code

    @field:Schema(description = "에러메시지")
    var message = message

    @field:JsonInclude(JsonInclude.Include.NON_EMPTY)
    var errors = errors

    @Schema(description = "필드 에러 정보")
    class ValidationError(field: String, message: String?) {

        @field:Schema(description = "필드명")
        var field = field

        @field:Schema(description = "필드 에러메시지")
        var message = message

        companion object {
            fun of(fieldError: FieldError): ValidationError {
                return ValidationError(
                        field = fieldError.field,
                        message = fieldError.defaultMessage
                )
            }
        }
    }
}