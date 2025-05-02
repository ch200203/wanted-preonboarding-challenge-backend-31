package com.study.wanted.product.service.query;

import com.study.wanted.product.entity.Product;
import com.study.wanted.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryHandler {

    private final ProductRepository productRepository;


    @Cacheable(cacheNames = "product:", key = "#id")
    public Product getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("조회 결과가 없습니다."));


        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("조회 결과가 없습니다."));
    }
}

record ProductResponseDto(
        Long id,
        String name,
        String slug,
        String shortDescription,
        String fullDescription,
        // Seller
        // Brand
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        // ProductDetailResponseDto detail,
        // ProductPriceResponseDto
        ) {


}

record ProductPriceResponseDto(

) {

}