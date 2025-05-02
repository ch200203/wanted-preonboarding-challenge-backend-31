package com.study.wanted.common.model;

public enum ApiResponseCode {
    SUCCESS("요청이 성공적으로 처리되었습니다");

    private final String message;

    ApiResponseCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
