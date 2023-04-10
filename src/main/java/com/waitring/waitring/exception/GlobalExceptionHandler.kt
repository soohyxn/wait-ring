package com.waitring.waitring.exception

import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.IllegalStateException
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import com.waitring.waitring.exception.ErrorResponse.ValidationError
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import java.util.stream.Collectors

@RestControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    // 부적절한 상태일 경우
    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalState(e: IllegalStateException): ResponseEntity<Any> {
        log.warn("handle IllegalStateException", e)
        return handleExceptionInternal(HttpStatus.NOT_FOUND, e.message)
    }

    // 유효성 검증에 실패한 경우
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgument(e: MethodArgumentNotValidException): ResponseEntity<Any> {
        log.warn("handle MethodArgumentNotValidException", e)
        return handleExceptionInternal(e, HttpStatus.BAD_REQUEST, e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleAllException(e: Exception): ResponseEntity<Any> {
        log.warn("handle Exception", e)
        return handleExceptionInternal(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
    }

    private fun handleExceptionInternal(httpStatus: HttpStatus, message: String?): ResponseEntity<Any> {
        return ResponseEntity
                .status(httpStatus)
                .body(makeErrorResponse(httpStatus, message))
    }

    private fun handleExceptionInternal(e: MethodArgumentNotValidException, httpStatus: HttpStatus, message: String): ResponseEntity<Any> {
        return ResponseEntity
                .status(httpStatus)
                .body(makeErrorResponse(e, httpStatus, message))
    }

    private fun makeErrorResponse(httpStatus: HttpStatus, message: String?): ErrorResponse {
        return ErrorResponse(
                code = httpStatus.name,
                message = message
        )
    }

    private fun makeErrorResponse(e: MethodArgumentNotValidException, httpStatus: HttpStatus, message: String): ErrorResponse {
        val validationErrorList = e.bindingResult.fieldErrors
                .stream()
                .map{fieldError -> ValidationError.of(fieldError)}
                .collect(Collectors.toList())

        return ErrorResponse(
                code = httpStatus.name,
                message = message,
                errors = validationErrorList
        )
    }
}