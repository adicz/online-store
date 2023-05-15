package com.project.gateway.service;

import com.project.gateway.model.ProductDto;
import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface GatewayProductService {
    SearchProductsResponse search(List<Long> ids,
                                  List<String> names,
                                  List<String> descriptions,
                                  List<String> categories,
                                  BigDecimal fromPrice,
                                  BigDecimal toPrice,
                                  Integer page,
                                  Integer size);

    CreateProductResponse create(ProductDto product);

    UpdateProductResponse update(ProductDto product);

    DeleteProductResponse delete(Long id);
}
