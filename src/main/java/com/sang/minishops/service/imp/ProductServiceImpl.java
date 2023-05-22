package com.sang.minishops.service.imp;
import com.sang.minishops.entity.Image;
import com.sang.minishops.entity.Product;
import com.sang.minishops.repository.ImageRepository;
import com.sang.minishops.repository.ProductRepository;
import com.sang.minishops.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  ImageRepository imageRepository;


    @Override
    public Product saveProduct(Product product, MultipartFile[] files) throws IOException {
        Set<Image> images = new HashSet<>();
        String imageUrl = null;

        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get("Image/",fileName);


            Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);

            Image image = new Image();
            image.setFileName(fileName);

            images.add(imageRepository.save(image));
            imageUrl = filePath.toString();


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
    public Product GetProductById(int id) {
        Optional<Product> optionalProduct= productRepository.findById(id);
        Product product=optionalProduct.get();
        return product;
    }

    @Override
    public void DeleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
