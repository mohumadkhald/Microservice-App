package com.projects.gateway.service;


import com.projects.gateway.dto.ProductRequest;
import com.projects.gateway.dto.ProductResponse;
import com.projects.gateway.model.Product;
import com.projects.gateway.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {

    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()

                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);

    }

    public List<ProductResponse> getAllProducts() {
        List <Product> products = productRepository.findAll();
        return products.stream().map(this::toProductResponse).collect(Collectors.toList());
    }

    private ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
