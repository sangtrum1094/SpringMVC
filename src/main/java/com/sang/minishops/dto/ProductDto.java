package com.sang.minishops.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productName;
    private String productDescription;
    private Double productDiscountedPrince;
    private Double productActualPrince;

    private List<MultipartFile> productImage;

}

