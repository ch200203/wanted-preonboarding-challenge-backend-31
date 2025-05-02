package com.study.wanted.common.dto;

import com.study.wanted.common.model.ApiResponseCode;

public record ApiResponseDto<T>(
        boolean success,
        T data,
        String message
) {
    public static ApiResponseDto successApiResponse(Object responseData) {
        return new ApiResponseDto(true, responseData, ApiResponseCode.SUCCESS.getMessage());
    }

    public static ApiResponseDto failApiResponse(Object responseData, String message) {
        return new ApiResponseDto(false, responseData, message);
    }
}
