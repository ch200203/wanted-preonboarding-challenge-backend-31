package com.study.wanted.product.dto;

public record CreateProductCommand(
        String name,
        String slug,
        String shortDescription,
        String fullDescription,
        Long sellerId,
        Long brandId,
        Double weight,
        String dimensions,
        String materials,
        String countryOfOrigin,
        String warrantyInfo,
        String careInstructions,
        String additionalInfo,
        Long basePrice,
        Long salePrice,
        Long costPrice,
        String currency,
        Double taxRate
) {
}
