package com.nogueira.loja.repository;

import com.nogueira.loja.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
