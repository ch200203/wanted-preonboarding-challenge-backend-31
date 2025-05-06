package com.study.wanted.product.repository;

import com.study.wanted.product.dto.ProductFilter;
import com.study.wanted.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    void findProductByFilter(ProductFilter filter, Pageable pageable);
}
