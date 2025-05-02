package com.study.wanted.product.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String slug;

    @Column(name = "short_description")
    private String shortDescription;

    @Lob
    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "brand_id")
    private Long brandId;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductDetail productDetail;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductPrice productPrice;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    private Product(String name, String slug, String shortDescription, String fullDescription,
                    Long sellerId, Long brandId, ProductStatus status,
                    ProductDetail productDetail, ProductPrice productPrice) {
        this.name = name;
        this.slug = slug;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.sellerId = sellerId;
        this.brandId = brandId;
        this.status = status;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
    }

    // 정적 팩토리 메서드 (Aggregate 생성용)
    public static Product create(String name, String slug, String shortDescription, String fullDescription,
                                 Long sellerId, Long brandId, ProductStatus status,
                                 ProductDetail productDetail, ProductPrice productPrice) {
        Product product = Product.builder()
                .name(name)
                .slug(slug)
                .shortDescription(shortDescription)
                .fullDescription(fullDescription)
                .sellerId(sellerId)
                .brandId(brandId)
                .status(status)
                .productDetail(productDetail)
                .productPrice(productPrice)
                .build();

        productDetail.setProduct(product);
        productPrice.setProduct(product);

        return product;
    }
}