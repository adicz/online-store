package com.project.gateway.service;

import com.project.gateway.model.ProductDto;
import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;

public interface GatewayProductService {
    SearchProductsResponse search(Long id);

    CreateProductResponse create(ProductDto product);

    UpdateProductResponse update(ProductDto product);

    DeleteProductResponse delete(Long id);
}
