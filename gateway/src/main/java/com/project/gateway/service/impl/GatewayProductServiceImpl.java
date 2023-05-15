package com.project.gateway.service.impl;

import com.project.api.products.ProductApi;
import com.project.gateway.exceptions.ProductNotFountException;
import lombok.RequiredArgsConstructor;
import com.project.gateway.model.ProductDto;
import com.project.model.products.enums.CreateProductStatus;
import com.project.model.products.enums.DeleteProductStatus;
import com.project.model.products.enums.SearchProductStatus;
import com.project.model.products.enums.UpdateProductStatus;
import com.project.model.products.request.CreateProductRequest;
import com.project.model.products.request.DeleteProductRequest;
import com.project.model.products.request.SearchProductsRequest;
import com.project.model.products.request.UpdateProductRequest;
import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;
import com.project.gateway.service.GatewayProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GatewayProductServiceImpl implements GatewayProductService {

    @Autowired
    private final ProductApi api;

    @Override
    public SearchProductsResponse search(Long id) {
        final SearchProductsRequest request = SearchProductsRequest.builder()
                .ids(List.of(id))
                .build();
        final SearchProductsResponse response = api.search(request);

        if (response.getStatus() == SearchProductStatus.OK) {
            return response;
        } else {
            throw new ProductNotFountException(id);
        }
    }

    @Override
    public CreateProductResponse create(ProductDto product) {
        final CreateProductRequest request = CreateProductRequest.builder()
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();
        final CreateProductResponse response = api.create(request);

        if (response.getStatus() == CreateProductStatus.OK) {
            return response;
        } else {
            throw new RuntimeException("Something goes wrong");
        }
    }

    @Override
    public UpdateProductResponse update(ProductDto product) {
        final UpdateProductRequest request = UpdateProductRequest.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();
        final UpdateProductResponse response = api.update(request);

        if (response.getStatus() == UpdateProductStatus.OK) {
            return response;
        } else {
            throw new ProductNotFountException(product.getId());
        }
    }

    @Override
    public DeleteProductResponse delete(Long id) {
        final DeleteProductRequest request = DeleteProductRequest.builder()
                .id(id)
                .build();
        final DeleteProductResponse response = api.delete(request);

        if (response.getStatus() == DeleteProductStatus.OK) {
            return response;
        } else {
            throw new ProductNotFountException(id);
        }
    }
}
