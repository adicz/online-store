package com.project.product.api

import com.project.api.products.ProductApi
import com.project.model.products.enums.CreateProductStatus
import com.project.model.products.request.CreateProductRequest
import com.project.product.ProductTestRepository
import com.project.product.mapper.ProductMapper
import com.project.product.mapper.ProductMapperImpl
import com.project.product.service.ProductService
import com.project.product.service.impl.ProductServiceImpl
import spock.lang.Specification

class ProductApi_unitTest extends Specification {

    ProductTestRepository repository = new ProductTestRepository()
    ProductMapper mapper = new ProductMapperImpl()
    ProductService service = new ProductServiceImpl(repository, mapper)
    ProductApi api = new ProductApiImpl(service)

    def setup() {
       repository.deleteAllInBatch()
    }

    def "should create new product"() {
        given:
        def request = CreateProductRequest.builder()
                .name("PlayStation 5")
                .description("(PlayStation 5) The latest Sony PlayStation introduced in November 2020.")
                .category("GAMING")
                .price(3020.20)
                .build()

        when:
        def response = api.create(request)

        then:
        response.getStatus() == CreateProductStatus.OK
        response.getCreatedProduct() != null
        response.getCreatedProduct().getId() != null
        response.getCreatedProduct().getTitle() == "PlayStation 5"
        response.getErrorMessage() == null

        repository.findAll().size() == 1
        repository.getById(1)
    }

}
