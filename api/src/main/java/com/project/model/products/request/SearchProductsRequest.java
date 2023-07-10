package com.project.model.products.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductsRequest {
    private List<Long> ids;
    private String text;
    private List<String> categories;
    private BigDecimal fromPrice;
    private BigDecimal toPrice;

    private Integer page;
    private Integer size;
}
