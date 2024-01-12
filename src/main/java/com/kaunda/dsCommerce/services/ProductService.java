package com.kaunda.dsCommerce.services;

import com.kaunda.dsCommerce.dto.ProductDTO;
import com.kaunda.dsCommerce.entities.Product;
import com.kaunda.dsCommerce.repository.ProductRepository;
import com.kaunda.dsCommerce.services.exceptions.DatabaseException;
import com.kaunda.dsCommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        var product=productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado!"));
        return  new ProductDTO(product);
    }
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> productList=productRepository.findAll(pageable);
        return productList.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        var product=new Product();
        copyDToToEntity(dto, product);
        product = productRepository.save(product);
        return  new ProductDTO(product);

    }

    @Transactional
    public ProductDTO update( Long id, ProductDTO dto){
      try{
          var product= productRepository.getReferenceById(id);
          copyDToToEntity(dto, product);
          product = productRepository.save(product);
          return  new ProductDTO(product);
      }catch (EntityNotFoundException e){
          throw new ResourceNotFoundException("Recurso não encontrado!");
      }

    }
    private void copyDToToEntity(ProductDTO productDTO, Product product){
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
