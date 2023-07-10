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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j(topic = "PRODUCT-MODULE")
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public SearchProductsResponse search(SearchProductsRequest request) {
        final ProductRepository.ProductFilter filter = ProductRepository.ProductFilter.builder()
                .ids(request.getIds())
                .text(request.getText())
                .categories(request.getCategories())
                .fromPrice(request.getFromPrice())
                .toPrice(request.getToPrice())
                .build();
        final PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        final Page<Product> productsPage = repository.findAll(filter, pageRequest)
                .map(mapper::mapToProduct);
        log.info("Returning list of {} products", productsPage.getTotalPages());
        return SearchProductsResponse.builder()
                .status(SearchProductStatus.OK)
                .products(productsPage.getContent())
                .totalPages(productsPage.getTotalPages())
                .totalElements(productsPage.getTotalElements())
                .build();
    }

    @Override
    public CreateProductResponse create(CreateProductRequest request) {
        final ProductEntity product = createProductEntity(request);
        final ProductEntity created = repository.save(product);
        log.info("Successfully create product with id: {}", product.getId());
        return CreateProductResponse.builder()
                .status(CreateProductStatus.OK)
                .createdProduct(mapper.mapToProduct(created))
                .build();
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest request) {
        if (repository.existsById(request.getId())) {

            return UpdateProductResponse.builder().status(UpdateProductStatus.OK).build();
        }
        return UpdateProductResponse.builder().status(UpdateProductStatus.NOT_FOUND).build();
    }

    private ProductEntity createProductEntity(CreateProductRequest request) {
        return ProductEntity.builder()
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .createTimestamp(OffsetDateTime.now())
                .updateTimestamp(OffsetDateTime.now())
                .build();
    }

    private ProductEntity createProductEntity(UpdateProductRequest request) {
        return ProductEntity.builder()
                .id(request.getId())
                .title(request.getName())
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
