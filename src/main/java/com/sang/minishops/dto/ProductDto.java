package com.sang.minishops.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productName;
    private String productDescription;
    private Double productDiscountedPrince;
    private Double productActualPrince;

    private List<MultipartFile> productImage;

}

