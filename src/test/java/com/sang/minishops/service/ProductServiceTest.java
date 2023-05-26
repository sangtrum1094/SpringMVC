package com.sang.minishops.service;

import com.sang.minishops.entity.Image;
import com.sang.minishops.entity.Product;
import com.sang.minishops.repository.ImageRepository;
import com.sang.minishops.repository.ProductRepository;
import com.sang.minishops.service.imp.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testSaveProduct() throws IOException {
        // Tạo mock MultipartFile
        MockMultipartFile[] files = new MockMultipartFile[2];
        files[0] = new MockMultipartFile("file1", "file1.jpg", "image/jpeg", new byte[]{1, 2, 3});
        files[1] = new MockMultipartFile("file2", "file2.jpg", "image/jpeg", new byte[]{4, 5, 6});

        // Tạo đối tượng Product
        Product product = new Product();
        product.setProductName("Test Product");
        // ...

        // Tạo đối tượng Image
        Image image1 = new Image();
        image1.setId(1);
        image1.setFileName("file1.jpg");

        Image image2 = new Image();
        image2.setId(2);
        image2.setFileName("file2.jpg");

        Set<Image> images = new HashSet<>();
        images.add(image1);
        images.add(image2);

        // Mock các phương thức và hành vi của các đối tượng mock
        Mockito.when(imageRepository.save(Mockito.any(Image.class))).thenReturn(image1, image2);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        // Gọi phương thức saveProduct
        Product savedProduct = productService.saveProduct(product, files);

        // Kiểm tra kết quả
        Mockito.verify(imageRepository, Mockito.times(2)).save(Mockito.any(Image.class));
        Mockito.verify(productRepository).save(Mockito.any(Product.class));

        // Assert các giá trị trong savedProduct


        // Assert các giá trị trong images của savedProduct
        // ...
    }
}