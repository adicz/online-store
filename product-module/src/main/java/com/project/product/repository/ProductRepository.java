package com.project.product.repository;

import com.project.product.model.ProductEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    @Builder
    @Getter
    @Setter
    class ProductFilter implements Specification<ProductEntity> {
        private final List<Long> ids;
        private final String text;
        private final List<String> categories;
        private final BigDecimal fromPrice;
        private final BigDecimal toPrice;

        @Override
        public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            final List<Predicate> filters = new LinkedList<>();

            if (!CollectionUtils.isEmpty(ids)) {
                filters.add(criteriaBuilder.in(root.get(ProductEntity.Fields.id)).value(ids));
            }
            if (text != null) {
                filters.add(criteriaBuilder.in(root.get(ProductEntity.Fields.title)).value(text));
                filters.add(criteriaBuilder.in(root.get(ProductEntity.Fields.description)).value(text));
            }
            if (!CollectionUtils.isEmpty(categories)) {
                filters.add(criteriaBuilder.in(root.get(ProductEntity.Fields.category)).value(categories));
            }
            if (fromPrice != null) {
                filters.add(criteriaBuilder.lessThanOrEqualTo(root.get(ProductEntity.Fields.price), fromPrice));
            }
            if (toPrice != null) {
                filters.add(criteriaBuilder.lessThanOrEqualTo(root.get(ProductEntity.Fields.price), toPrice));
            }
            filters.add(criteriaBuilder.greaterThan(root.get(ProductEntity.Fields.availability), 0));

            return criteriaBuilder.and(filters.toArray(new Predicate[0]));
        }
    }
}
