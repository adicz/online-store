package com.project.gateway.service.impl;

import com.project.api.products.ProductApi;
import com.project.gateway.model.ProductDto;
import com.project.gateway.service.GatewayProductService;
import com.project.model.products.request.CreateProductRequest;
import com.project.model.products.request.DeleteProductRequest;
import com.project.model.products.request.SearchProductsRequest;
import com.project.model.products.request.UpdateProductRequest;
import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j(topic = "GATEWAY")
@Service
@RequiredArgsConstructor
public class GatewayProductServiceImpl implements GatewayProductService {

    @Autowired
    private final ProductApi api;

    @Override
    public SearchProductsResponse search(List<Long> ids,
                                         List<String> names,
                                         List<String> descriptions,
                                         List<String> categories,
                                         BigDecimal fromPrice,
                                         BigDecimal toPrice,
                                         Integer page,
                                         Integer size) {
        final SearchProductsRequest request = SearchProductsRequest.builder()
                .ids(ids)
                .categories(categories)
                .fromPrice(fromPrice)
                .toPrice(toPrice)
                .page(page)
                .size(size)
                .build();
        log.info("Sending request with body {}", request);
        final SearchProductsResponse response = api.search(request);
        log.info("Got response with status {}", response.getStatus());

        return response;

    }

    @Override
    public CreateProductResponse create(ProductDto product) {
        final CreateProductRequest request = CreateProductRequest.builder()
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();
        log.info("Sending request with body {}", request);
        final CreateProductResponse response = api.create(request);
        log.info("Got response with status {}", response.getStatus());

        return response;
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
        log.info("Sending request with body {}", request);
        final UpdateProductResponse response = api.update(request);
        log.info("Got response with status {}", response.getStatus());

        return response;
    }

    @Override
    public DeleteProductResponse delete(Long id) {
        final DeleteProductRequest request = DeleteProductRequest.builder()
                .id(id)
                .build();
        log.info("Sending request with body {}", request);
        final DeleteProductResponse response = api.delete(request);
        log.info("Got response with status {}", response.getStatus());

        return response;
    }
}
