package com.sang.minishops.service.imp;

import com.sang.minishops.entity.Image;
import com.sang.minishops.entity.Product;
import com.sang.minishops.repository.ImageRepository;
import com.sang.minishops.repository.ProductRepository;
import com.sang.minishops.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;


    @Override
    public Product saveProduct(Product product, MultipartFile[] files) throws IOException {
        Set<Image> images = new HashSet<>();
        String imageUrl = null;

        for (MultipartFile file : files) {
            if (file != null && file.getOriginalFilename() != null) {
                String  fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path filePath = Paths.get("Image/", fileName);


                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                Image image = new Image();
                image.setFileName(fileName);
                imageRepository.save(image);
                images.add(image);
                imageUrl = filePath.toString();
            }
        }
        product.setImages(images);
        product.setImageUrl(imageUrl);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void saveProductCsv(Product product) {
        productRepository.save(product);
    }

}
