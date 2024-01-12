package com.kaunda.dsCommerce.services;

import com.kaunda.dsCommerce.dto.ProductDTO;
import com.kaunda.dsCommerce.entities.Product;
import com.kaunda.dsCommerce.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        var product=productRepository.findById(id).get();
        return  new ProductDTO(product);


    }
}
