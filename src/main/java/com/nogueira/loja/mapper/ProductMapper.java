package com.nogueira.loja.mapper;

import com.nogueira.loja.dto.ProductDTO;
import com.nogueira.loja.form.FormProduct;
import com.nogueira.loja.model.Product;

import java.math.BigDecimal;

public abstract class ProductMapper {

    public static Product convertDTOForEntity(ProductDTO productDTO){
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCreated(productDTO.getCreated());
        product.setUpdated(productDTO.getUdpated());
        product.setValue(productDTO.getValue());

        return product;
    }

    public static ProductDTO convertEntityForDTO(Product product){

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setValue(product.getValue());
        productDTO.setDescription(product.getDescription());
        productDTO.setCreated(product.getCreated());
        productDTO.setUdpated(product.getUpdated());

        return productDTO;
    }


    public static ProductDTO convertFormForDTO(FormProduct formProduct){
        ProductDTO productDTO = createBaseDTOWithFormProduct(formProduct);
        return productDTO;
    }

    public static ProductDTO convertFormForDTO(Long id,FormProduct formProduct){
        ProductDTO productDTO = createBaseDTOWithFormProduct(formProduct);
        productDTO.setId(id);
        return productDTO;
    }

    private static ProductDTO createBaseDTOWithFormProduct(FormProduct formProduct){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setDescription(formProduct.getDescription());
        productDTO.setName(formProduct.getName());
        productDTO.setValue(formProduct.getValue());

        return productDTO;
    }

    public static void build(Product product, ProductDTO productDTO){
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setValue(productDTO.getValue());
    }

}
