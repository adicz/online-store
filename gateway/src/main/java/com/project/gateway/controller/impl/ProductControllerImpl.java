package com.project.gateway.controller.impl;

import com.project.gateway.controller.ProductController;
import com.project.gateway.model.ProductDto;
import com.project.gateway.service.GatewayProductService;
import lombok.RequiredArgsConstructor;

import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    @Autowired
    private final GatewayProductService service;


    @Override
    public SearchProductsResponse search(Long id) {
        return service.search(id);
    }

    @Override
    public CreateProductResponse create(ProductDto product) {
        return service.create(product);
    }

    @Override
    public UpdateProductResponse update(ProductDto product) {
        return service.update(product);
    }

    @Override
    public DeleteProductResponse delete(Long id) {
        return service.delete(id);
    }
}

