package com.nogueira.loja.dto;

import com.nogueira.loja.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductDTO extends BaseDTO {

    private Long id;
    private String name;
    private BigDecimal value;
    private String description;



}
