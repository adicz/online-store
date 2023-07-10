package com.project.model.products.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.model.products.enums.SearchProductStatus;
import com.project.model.products.model.Product;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductsResponse {
    private SearchProductStatus status;
    private List<Product> products;
    private Long totalElements;
    private Integer totalPages;
    private String errorMessage;
}
