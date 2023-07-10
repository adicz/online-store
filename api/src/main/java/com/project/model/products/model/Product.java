package com.project.model.products.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String description;
    private String category;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private Integer availability;
    private OffsetDateTime createTimestamp;
    private OffsetDateTime updateTimestamp;
    private Integer version;
}
