package com.kaunda.dsCommerce.controllers;

import com.kaunda.dsCommerce.dto.ProductDTO;
import com.kaunda.dsCommerce.entities.Product;
import com.kaunda.dsCommerce.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id ){
        return productService.findById(id);
    }
}
