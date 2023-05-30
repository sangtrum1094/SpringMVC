package com.sang.minishops.convert;

import com.sang.minishops.dto.ProductCsvDto;
import com.sang.minishops.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductCsvDtoToProduct {


    public Product convertProductCsvDtotoProduct(ProductCsvDto productCsvDto){
        Product product = new Product();
        product.setProductName(productCsvDto.getProductName());
        product.setProductDescription(productCsvDto.getProductDescription());
        product.setProductActualPrince(productCsvDto.getProductActualPrince());
        product.setProductDiscountedPrince(productCsvDto.getProductDiscountedPrince());
        product.setImageUrl(productCsvDto.getImageUrl());
        return product;
    }
}
