package com.project.model.products.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.model.products.enums.DeleteProductStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductResponse {
    private DeleteProductStatus status;
}
