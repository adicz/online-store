package com.project.product.mapper;

import com.project.model.products.model.Product;
import com.project.product.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapToProduct(ProductEntity entity);

}
