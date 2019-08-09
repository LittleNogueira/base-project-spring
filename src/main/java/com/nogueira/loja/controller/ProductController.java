package com.nogueira.loja.controller;

import com.nogueira.loja.dto.ProductDTO;
import com.nogueira.loja.form.FormProduct;
import com.nogueira.loja.mapper.ProductMapper;
import com.nogueira.loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Cacheable(value = "pageProducts") //Use de cache deve ser bem pensado, pois pode trazer baixa perfomace para o seu projeto
    public ResponseEntity<Page<ProductDTO>> page(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok().body(this.productService.page(pageable));
    }

    @PostMapping
    @CacheEvict(value = "pageProducts", allEntries = true)
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid FormProduct form, UriComponentsBuilder uriComponentsBuilder){
        ProductDTO productDTO = productService.create(ProductMapper.convertFormForDTO(form));
        URI uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(productDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(productDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @PutMapping(value = "/{id}")
    @CacheEvict(value = "pageProducts", allEntries = true)
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody @Valid FormProduct formProduct){
        return ResponseEntity.ok().body(productService.update(ProductMapper.convertFormForDTO(id,formProduct)));
    }

    @DeleteMapping(value = "/{id}")
    @CacheEvict(value = "pageProducts", allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
