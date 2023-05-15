package com.project.gateway.mapper;

import com.project.gateway.exceptions.ProductNotFountException;
import jakarta.annotation.PostConstruct;
import com.project.model.products.enums.SearchProductStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StatusToExceptionMapper {

    private HashMap<Enum<?>, Class<? extends RuntimeException>> statusToException = new HashMap<>();

    @PostConstruct
    private void init() {
        statusToException.put(SearchProductStatus.NOT_FOUND, ProductNotFountException.class);
    }

}
