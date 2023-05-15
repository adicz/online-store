package com.project.model.products.response;

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
}
