package com.example.promotion_management_system.util.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        int status,
        String message,
        Map<String, String> errors
) {
    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message, null);
    }

    public static ErrorResponse of(int status, String message, Map<String, String> errors) {
        return new ErrorResponse(status, message, errors);
    }
}