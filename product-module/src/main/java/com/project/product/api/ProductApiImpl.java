package com.project.product.api;

import com.project.api.products.ProductApi;
import com.project.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import com.project.model.products.request.CreateProductRequest;
import com.project.model.products.request.DeleteProductRequest;
import com.project.model.products.request.SearchProductsRequest;
import com.project.model.products.request.UpdateProductRequest;
import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProductApiImpl implements ProductApi {

    @Autowired
    private final ProductService service;

    @Override
    public SearchProductsResponse search(SearchProductsRequest request) {
        return service.search(request);
    }

    @Override
    public CreateProductResponse create(CreateProductRequest request) {
        return service.create(request);
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest request) {
        return service.update(request);
    }

    @Override
    public DeleteProductResponse delete(DeleteProductRequest request) {
        return service.delete(request);
    }
}
