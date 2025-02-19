package com.exercises.SimpleAPI.exception;

import com.exercises.SimpleAPI.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException ex) {
        ErrorCode ErrorCode = ex.getErrorCode();
//        return ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(ex.getErrorCode());
        return ResponseEntity.status(ErrorCode.getHttpStatus()).body(ApiResponse.builder().code(ErrorCode.getCode()).message(ErrorCode.getMessage()).build());
    }
}