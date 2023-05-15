package com.project.gateway.controller;

import com.project.gateway.model.ProductDto;
import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;
import org.springframework.web.bind.annotation.*;


public interface ProductController {

    @GetMapping("/{id}")
    SearchProductsResponse search(@PathVariable Long id);

    @PostMapping
    CreateProductResponse create(@RequestBody ProductDto product);

    @PutMapping("/{id}")
    UpdateProductResponse update(@RequestBody ProductDto product);

    @DeleteMapping("/{id}")
    DeleteProductResponse delete(@PathVariable Long id);

}
