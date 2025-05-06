package com.study.wanted.product.service.query;

import com.study.wanted.product.dto.ProductFilter;
import com.study.wanted.product.dto.ProductsResponseDto;
import com.study.wanted.product.dto.ProductResponseDto;
import com.study.wanted.product.entity.Product;
import com.study.wanted.product.repository.ProductCustomRepository;
import com.study.wanted.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryHandler {

    private final ProductRepository productRepository;
    private final ProductCustomRepository productCustomRepository;


    @Cacheable(cacheNames = "product:", key = "#id")
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("조회 결과가 없습니다."));
        return toProductResponseDto(product);
    }

    private ProductResponseDto toProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .fullDescription(product.getFullDescription())
                .shortDescription(product.getShortDescription())
                .slug(product.getSlug())
                .status(product.getStatus().name())
                .updatedAt(product.getUpdatedAt())
                .createdAt(product.getCreatedAt())
                .build();
    }

    @Cacheable(value = "product_list", key = "#filter.cacheKey()")
    public ProductsResponseDto getProductLists(ProductFilter filter) {
        Pageable pageable = PageRequest.of(filter.page() - 1, filter.perPage(), filter.sort());

        Page<Product> page = productCustomRepository.findProductsByFilter(filter, pageable);

        List<ProductsResponseDto> productsResponseDtos = page.getContent().stream()
                .map(it -> new ProductResponseDto(
                        it.getId(),
                        it.getName(),
                        it.getSlug(),
                        it.getShortDescription(),
                        it.getProductPrice().getBasePrice(),

                ))
    }
}
