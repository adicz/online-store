package com.project.model.products.response;

import com.project.model.products.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.model.products.enums.UpdateProductStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductResponse {
    private UpdateProductStatus status;
    private Product updatedProduct;
    private String errorMessage;
}
