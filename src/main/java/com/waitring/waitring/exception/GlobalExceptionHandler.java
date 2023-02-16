package com.waitring.waitring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 부적절한 상태일 경우
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalState(IllegalStateException e) {
        log.warn("handle IllegalStateException", e);
        return handleExceptionInternal(HttpStatus.NOT_FOUND, e.getMessage());
    }

    // 유효성 검증에 실패한 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgument(MethodArgumentNotValidException e){
        log.warn("handle IllegalStateException", e);
        return handleExceptionInternal(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception e) {
        log.warn("handle Exception", e);
        return handleExceptionInternal(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<Object> handleExceptionInternal(HttpStatus httpStatus, String message) {
        return ResponseEntity
                .status(httpStatus)
                .body(makeErrorResponse(httpStatus, message));
    }

    private ResponseEntity<Object> handleExceptionInternal(MethodArgumentNotValidException e, HttpStatus httpStatus, String message) {
        return ResponseEntity
                .status(httpStatus)
                .body(makeErrorResponse(e, httpStatus, message));
    }

    private ErrorResponse makeErrorResponse(HttpStatus httpStatus, String message) {
        return ErrorResponse.builder()
                .code(httpStatus.name())
                .message(message)
                .build();
    }

    private ErrorResponse makeErrorResponse(MethodArgumentNotValidException e, HttpStatus httpStatus, String message) {
        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .code(httpStatus.name())
                .message(message)
                .errors(validationErrorList)
                .build();
    }
}
