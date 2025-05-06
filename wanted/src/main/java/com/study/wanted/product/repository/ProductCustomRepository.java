package com.study.wanted.product.repository;

import com.study.wanted.product.dto.ProductFilter;
import com.study.wanted.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductCustomRepository {
    Page<Product> findProductsByFilter(ProductFilter filter, Pageable pageable);
}
