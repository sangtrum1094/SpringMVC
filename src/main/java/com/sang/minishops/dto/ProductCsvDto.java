package com.sang.minishops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCsvDto {
    private String productName;

    private String productDescription;

    private Double productDiscountedPrince;

    private Double productActualPrince;

    private String imageUrl;

}
