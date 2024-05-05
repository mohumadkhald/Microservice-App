package com.projects.productservice.controller;


import com.projects.productservice.service.ProductServiceImpl;
import com.projects.productservice.dto.ProductRequest;
import com.projects.productservice.dto.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

    private final Environment environment;

    private final ProductServiceImpl productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest)
    {

        productService.createProduct(productRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        System.out.println(environment.getProperty("local.server.port"));
        return productService.getAllProducts();
    }
}
