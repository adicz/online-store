package com.project.model.products.response;

import com.project.model.products.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.model.products.enums.CreateProductStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {
    private CreateProductStatus status;
    private Product createdProduct;
    private String errorMessage;
}
