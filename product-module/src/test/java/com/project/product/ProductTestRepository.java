package com.project.product;

import com.project.InMemoryJpaSpecificationExecutor;
import com.project.product.model.ProductEntity;
import com.project.product.repository.ProductRepository;
import groovy.lang.Singleton;

@Singleton
public class ProductTestRepository extends InMemoryJpaSpecificationExecutor<ProductEntity, Long> implements ProductRepository {

}
