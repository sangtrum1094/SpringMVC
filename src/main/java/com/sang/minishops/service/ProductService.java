package com.sang.minishops.service;

import com.sang.minishops.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product saveProduct(Product product, MultipartFile[] files) throws IOException;

    List<Product> getAllProduct();

    Product getProductById(int id);

    void deleteProduct(int id);

    void saveProductCsv(Product product);
}
