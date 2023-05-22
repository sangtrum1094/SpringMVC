package com.sang.minishops.controller;

import com.sang.minishops.dto.ProductDto;
import com.sang.minishops.entity.Image;
import com.sang.minishops.entity.Product;
import com.sang.minishops.service.ImageService;
import com.sang.minishops.service.ProductService;
import com.sang.minishops.service.imp.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;

    @GetMapping("/admin/addproduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @PostMapping("/admin/addproduct")
    public String addProduct(@ModelAttribute("productDto") ProductDto productDto, Model model) throws IOException {
        MultipartFile[] files = productDto.getProductImage().toArray(new MultipartFile[0]);

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductDiscountedPrince(productDto.getProductDiscountedPrince());
        product.setProductActualPrince(productDto.getProductActualPrince());
        productService.saveProduct(product, files);
        model.addAttribute("message", "Product added successfully!");
        return "redirect:/listproduct";
    }

    @GetMapping("/listproduct")
    public String showProducList(Model model) {
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("products", productList);
        return "listproduct";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            Product product = productService.GetProductById(id);
            String rootPath = request.getContextPath();
            String imagePath = product.getImageUrl();
            String imageUrl = rootPath + "/" + imagePath;
            product.setImageUrl(imageUrl);
            model.addAttribute("product", product);
            return "productdetail";
        } catch (Exception e) {
            return "redirect:error/404";
        }
    }

    @PostMapping("/deleteproduct")
    public String DeleteProductById(@RequestParam("id") int id) {
        Product product = productService.GetProductById(id);
        Set<Image> images = product.getImages();
        for (Image image : images) {
                imageService.deleteById(image.getId());
        }
        productService.DeleteProduct(id);
        return "redirect:/listproduct";
    }

    @GetMapping("/home")
    public String showListProduct(Model model) {
        List<Product> listProduct = productService.getAllProduct();
        model.addAttribute("listproduct", listProduct);
        UserDetailService userDetailDevices = new UserDetailService();
        String currentUsername = userDetailDevices.getCurrentUsername();
        model.addAttribute("username", currentUsername);
        return "home";
    }

}