package com.study.wanted.product.api.controller;

import com.study.wanted.common.dto.ApiResponseDto;
import com.study.wanted.product.api.dto.CreateProductRequest;
import com.study.wanted.product.entity.Product;
import com.study.wanted.product.service.query.ProductQueryHandler;
import com.study.wanted.product.service.command.ProductCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductCommandHandler productCommandHandler;
    private final ProductQueryHandler productQueryHandler;

    @PostMapping
    public ResponseEntity<ApiResponseDto> creteProduct(@RequestBody CreateProductRequest request) {
        Long productId = productCommandHandler.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.successApiResponse(productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getProduct(@PathVariable Long id) {
        productQueryHandler.getProduct(id);
        ApiResponseDto apiResponseDto = ApiResponseDto.successApiResponse();
        return ResponseEntity.ok(apiResponseDto);
    }

    /*
    GET /api/products: 상품 목록 조회 (핕터 조건 적용, 정렬, 페이지네이션)
    검색 기능 포함
    필터 항목: 태그, 등록일, 판매자, 브랜드, 가격 범위, 카테고리, 재고 유무
     */


}
