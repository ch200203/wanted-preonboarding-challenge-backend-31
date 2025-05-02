package com.study.wanted.product.service.command;

import com.study.wanted.product.api.dto.CreateProductRequest;
import com.study.wanted.product.entity.Product;
import com.study.wanted.product.entity.ProductDetail;
import com.study.wanted.product.entity.ProductPrice;
import com.study.wanted.product.entity.ProductStatus;
import com.study.wanted.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandHandler {

    private final ProductRepository productRepository;

    public Long createProduct(CreateProductRequest request) {
        ProductDetail productDetail = ProductDetail.of(
                request.weight(),
                request.dimensions(),
                request.materials(),
                request.countryOfOrigin(),
                request.warrantyInfo(),
                request.careInstructions(),
                request.additionalInfo()
        );

        ProductPrice productPrice = ProductPrice.of(
                request.basePrice(),
                request.salePrice(),
                request.costPrice(),
                request.currency(),
                request.taxRate()
        );

        Product product = Product.create(
                request.name(),
                request.slug(),
                request.shortDescription(),
                request.fullDescription(),
                request.sellerId(),
                request.brandId(),
                ProductStatus.AVAILABLE,
                productDetail,
                productPrice
        );

        productRepository.save(product);

        return product.getId();
    }


}
