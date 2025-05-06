package com.study.wanted.product.dto;

import java.time.LocalDateTime;

public record ProductsResponseDto(
        Long id,
        String name,
        String slug,
        String shortDescription,
        int basePrice,
        int salePrice,
        String currency,
        PrimaryImage primaryImage,
        Brand brand,
        Seller seller,
        double rating,
        int reviewCount,
        boolean inStock,
        String status,
        LocalDateTime createdAt
) {
    // TODO 임시로 만들어둠
    public record PrimaryImage(String url, String altText) {
    }

    public record Brand(Long id, String name) {
    }

    public record Seller(Long id, String name) {
    }
}
