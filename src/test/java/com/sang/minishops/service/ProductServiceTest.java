package com.sang.minishops.service;

import com.sang.minishops.entity.Image;
import com.sang.minishops.entity.Product;
import com.sang.minishops.repository.ImageRepository;
import com.sang.minishops.repository.ProductRepository;
import com.sang.minishops.service.imp.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        product.setProductActualPrince(2.0);
        product.setProductDescription("Test mo ta");
        product.setProductDiscountedPrince(1.0);
        product.setImageUrl("Image/file1");

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
        when(imageRepository.save(Mockito.any(Image.class))).thenReturn(image1, image2);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        // Gọi phương thức saveProduct
        Product savedProduct = productService.saveProduct(product, files);

        // Kiểm tra kết quả
        Mockito.verify(imageRepository, Mockito.times(2)).save(Mockito.any(Image.class));
        Mockito.verify(productRepository).save(Mockito.any(Product.class));

    }

    @Test
    public void testGetAllProduct(){
        // tạo danh sách mẫu để trả về từ Repository
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1,"Product1"));
        productList.add(new Product(2,"Product2"));
        when(productRepository.findAll()).thenReturn(productList);

        // Gọi phương thức kiểm tra thử
         List<Product> list= productService.getAllProduct();
        // Kiểm tra kết quả
        assertEquals(2,list.size());
        assertEquals("Product1",list.get(0).getProductName());
        assertEquals("Product2", list.get(1).getProductName());
    }
    @Test
    public void testGetProductById() {
        // Tạo đối tượng Product mẫu để trả về từ Repository
        Product expectedProduct = new Product();
        expectedProduct.setId(1);
        expectedProduct.setProductName("Product1");

        // Thiết lập hành vi cho productRepository.findById(id)
        when(productRepository.findById(1)).thenReturn(Optional.ofNullable(expectedProduct));

        // Gọi phương thức kiểm tra thử
        Product actualProduct = productService.GetProductById(1);

        // Kiểm tra kết quả
        assertEquals(expectedProduct, actualProduct);
        assertEquals("Product1", actualProduct.getProductName());
    }
    @Test
    public void testDeleteProduct() {

        int productId = 1;

        // Gọi phương thức cần kiểm tra
        productService.DeleteProduct(productId);

        // Kiểm tra xem phương thức deleteById đã được gọi với đúng tham số chưa
        verify(productRepository).deleteById(productId);
    }
    @Test
    public void testSaveProductCsv() {
        Product product = new Product();
        // Thiết lập các thuộc tính của product
        product.setProductName("Áo thun 1");
        product.setProductDescription("mo ta 1");
        product.setProductDiscountedPrince(2.0);

        // Gọi phương thức cần kiểm tra
        productService.saveProductCsv(product);

        // Kiểm tra xem phương thức save đã được gọi với đúng đối tượng product chưa
        verify(productRepository).save(product);
    }

}