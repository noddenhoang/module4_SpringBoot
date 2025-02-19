package com.exercises.SimpleAPI.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    EMPLOYEE_NOT_EXIST(40401, "Employee not exist", HttpStatus.NOT_FOUND),
    ;
    int code;
    String message;
    HttpStatus httpStatus;
}

