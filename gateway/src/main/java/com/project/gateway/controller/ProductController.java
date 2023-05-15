package com.project.gateway.controller;

import com.project.gateway.model.ProductDto;
import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


public interface ProductController {

    @GetMapping("/search")
    SearchProductsResponse search(@RequestParam(required = false) List<Long> ids,
                                  @RequestParam(required = false) List<String> names,
                                  @RequestParam(required = false) List<String> descriptions,
                                  @RequestParam(required = false) List<String> categories,
                                  @RequestParam(required = false) BigDecimal fromPrice,
                                  @RequestParam(required = false) BigDecimal toPrice,
                                  @RequestParam(defaultValue = "${gateway.controller.products.default.page}") Integer page,
                                  @RequestParam(defaultValue = "${gateway.controller.products.default.size}") Integer size);

    @PostMapping
    CreateProductResponse create(@RequestBody ProductDto product);

    @PutMapping("/{id}")
    UpdateProductResponse update(@RequestBody ProductDto product);

    @DeleteMapping("/{id}")
    DeleteProductResponse delete(@PathVariable Long id);
}
