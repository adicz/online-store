package com.project.api.products;

import com.project.model.products.request.CreateProductRequest;
import com.project.model.products.request.DeleteProductRequest;
import com.project.model.products.request.SearchProductsRequest;
import com.project.model.products.request.UpdateProductRequest;
import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;

public interface ProductApi {
    SearchProductsResponse search(SearchProductsRequest request);

    CreateProductResponse create(CreateProductRequest request);

    UpdateProductResponse update(UpdateProductRequest request);

    DeleteProductResponse delete(DeleteProductRequest request);
}
