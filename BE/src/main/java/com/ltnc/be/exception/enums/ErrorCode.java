package com.ltnc.be.exception.enums;

import com.ltnc.be.dto.error.ErrorResponse;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum ErrorCode {
    // === AUTH (1000)
    // ============================================================================================================
    PASSWORD_DOES_NOT_MATCH("1001", "Password does not match.");

    // === USER (1100)
    // ============================================================================================================

    private static final Map<String, ErrorCode> errorMap = Arrays.stream(values())
            .collect(Collectors.toMap(ErrorCode::getCode, e -> e));
    private String code;

    private String errorMessage;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.errorMessage = msg;
    }

    public static ErrorCode findByCode(String code) {
        return errorMap.get(code);
    }

    public ErrorResponse getErrorResponse() {
        return ErrorResponse.builder()
                .code(code)
                .errors(errorMessage)
                .build();
    }

    public Map<String, String> getErrorMap() {
        return new HashMap<>() {
            {
                put("code", code);
                put("message", errorMessage);
            }
        };
    }

    public Map<String, String> getErrorMap(String detail) {
        return new HashMap<>() {
            {
                put("code", code);
                put("message", errorMessage);
                put("detail", detail);
            }
        };
    }

}
