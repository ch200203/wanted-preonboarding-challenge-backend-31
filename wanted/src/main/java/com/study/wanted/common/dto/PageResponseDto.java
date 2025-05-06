package com.study.wanted.common.dto;

public record PageResponseDto<T>(
        T items,
        Pagination pagination
) {
    record Pagination(
            int totalItems,
            int totalPages,
            int currentPage,
            int perPage
    ) { }
}
