package com.kaunda.dsCommerce.controllers;

import com.kaunda.dsCommerce.entities.Product;
import com.kaunda.dsCommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String teste(){
      Optional<Product>resultado=productRepository.findById(2L);
      var prodduto= resultado.get();
      return prodduto.getName();
    }
}
