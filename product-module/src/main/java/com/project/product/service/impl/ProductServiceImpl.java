package com.project.product.service.impl;

import com.project.model.products.enums.CreateProductStatus;
import com.project.model.products.enums.DeleteProductStatus;
import com.project.model.products.enums.SearchProductStatus;
import com.project.model.products.enums.UpdateProductStatus;
import com.project.model.products.model.Product;
import com.project.model.products.request.CreateProductRequest;
import com.project.model.products.request.DeleteProductRequest;
import com.project.model.products.request.SearchProductsRequest;
import com.project.model.products.request.UpdateProductRequest;
import com.project.model.products.response.CreateProductResponse;
import com.project.model.products.response.DeleteProductResponse;
import com.project.model.products.response.SearchProductsResponse;
import com.project.model.products.response.UpdateProductResponse;
import com.project.product.mapper.ProductMapper;
import com.project.product.model.ProductEntity;
import com.project.product.repository.ProductRepository;
import com.project.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository repository;
    @Autowired
    private final ProductMapper mapper;

    @Override
    public SearchProductsResponse search(SearchProductsRequest request) {
        final ProductRepository.ProductFilter filter = ProductRepository.ProductFilter.builder()
                .ids(request.getIds())
                .names(request.getNames())
                .categories(request.getCategories())
                .fromPrice(request.getFromPrice())
                .toPrice(request.getToPrice())
                .build();
        final List<Product> products = repository.findAll(filter)
                .stream().map(mapper::mapToProduct).toList();
        return SearchProductsResponse.builder()
                .status(SearchProductStatus.OK)
                .products(products)
                .build();
    }

    @Override
    public CreateProductResponse create(CreateProductRequest request) {
        final ProductEntity product = createProductEntity(request);
        repository.save(product);
        return CreateProductResponse.builder().status(CreateProductStatus.OK).build();
    }

    private ProductEntity createProductEntity(CreateProductRequest request) {
        return ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest request) {
        if (repository.existsById(request.getId())) {
            final ProductEntity product = createProductEntity(request);
            repository.save(product);
            return UpdateProductResponse.builder().status(UpdateProductStatus.OK).build();
        }
        return UpdateProductResponse.builder().status(UpdateProductStatus.NOT_FOUND).build();
    }

    private ProductEntity createProductEntity(UpdateProductRequest request) {
        return ProductEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();
    }

    @Override
    public DeleteProductResponse delete(DeleteProductRequest request) {
        if (repository.existsById(request.getId())) {
            repository.deleteById(request.getId());
            return DeleteProductResponse.builder().status(DeleteProductStatus.OK).build();
        }
        return DeleteProductResponse.builder().status(DeleteProductStatus.NOT_FOUND).build();
    }
}
