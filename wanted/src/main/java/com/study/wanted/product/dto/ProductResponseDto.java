package com.study.wanted.product.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductResponseDto(
        Long id,
        String name,
        String slug,
        String shortDescription,
        String fullDescription,
        // Seller
        // Brand
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
        // ProductDetailResponseDto detail,
        // ProductPriceResponseDto
) {
}
