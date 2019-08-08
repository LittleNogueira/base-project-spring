package com.nogueira.loja.service;

import com.nogueira.loja.dto.ProductDTO;
import com.nogueira.loja.mapper.ProductMapper;
import com.nogueira.loja.model.Product;
import com.nogueira.loja.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService extends BaseService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> findAll(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::convertEntityForDTO).collect(Collectors.toList());
    }

    public Page<ProductDTO> page(Pageable pageable){
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductMapper::convertEntityForDTO);
    }

    public ProductDTO create(ProductDTO productDTO){
        Product product = ProductMapper.convertDTOForEntity(productDTO);
        productRepository.save(product);
        return ProductMapper.convertEntityForDTO(product);
    }

    public ProductDTO findById(Long id){
        Optional<Product> product = productRepository.findById(id);
        verifyErroNotFound(product);
        return ProductMapper.convertEntityForDTO(product.get());
    }

    public ProductDTO update(ProductDTO productDTO){
        Optional<Product> productOptional  = productRepository.findById(productDTO.getId());
        verifyErroNotFound(productOptional);

        Product product = productOptional.get();

        ProductMapper.build(product,productDTO);
        productRepository.save(product);
        return ProductMapper.convertEntityForDTO(product);
    }

    public void delete(Long id){
        Optional<Product> product = productRepository.findById(id);
        verifyErroNotFound(product);
        this.productRepository.delete(product.get());
    }

}
