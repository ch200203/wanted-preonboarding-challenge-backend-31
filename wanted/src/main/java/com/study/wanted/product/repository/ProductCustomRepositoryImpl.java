package com.study.wanted.product.repository;

import com.study.wanted.product.dto.ProductFilter;
import com.study.wanted.product.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final EntityManager em;

    // TODO Criteria -> QueryDSL 로 전환
    @Override
    public Page<Product> findProductsByFilter(ProductFilter filter, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        // 상태 필터
        if (filter.status() != null) {
            predicates.add(cb.equal(product.get("status"), filter.status()));
        }

        // 카테고리 (JOIN 필요 → 예시 생략)
        // 판매자, 브랜드, 가격 범위, 재고 유무 등도 마찬가지

        cq.where(predicates.toArray(new Predicate[0]));

        // 정렬
        if (pageable.getSort().isSorted()) {
            pageable.getSort().forEach(order -> {
                Path<Object> path = product.get(order.getProperty());
                cq.orderBy(order.isAscending() ? cb.asc(path) : cb.desc(path));
            });
        }

        TypedQuery<Product> query = em.createQuery(cq);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Product> resultList = query.getResultList();

        // Count Query
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Product.class))).where(predicates.toArray(new Predicate[0]));

        Long total = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, total);
    }
}
