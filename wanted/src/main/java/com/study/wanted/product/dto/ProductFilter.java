package com.study.wanted.product.dto;

import com.study.wanted.common.cache.CacheKey;
import org.springframework.data.domain.Sort;

public record ProductFilter(
        int page,
        int perPage,
        String status,
        Long categoryId,
        Long sellerId,
        Long brandId,
        Integer minPrice,
        Integer maxPrice,
        Boolean inStock,
        String search
) implements CacheKey {
    public Sort sort() {
        return Sort.by(Sort.Direction.DESC, "id");
    }

    // 페이지별로 캐싱
    @Override
    public String cacheKey() {
        return "product:" + this.hashCode() + ":" + this.page;
    }
}
