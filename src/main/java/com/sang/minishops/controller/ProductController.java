package com.sang.minishops.controller;

import com.sang.minishops.convert.ProductCsvDtoToProduct;
import com.sang.minishops.dto.ProductCsvDto;
import com.sang.minishops.dto.ProductDto;
import com.sang.minishops.entity.Image;
import com.sang.minishops.entity.Product;
import com.sang.minishops.service.ImageService;
import com.sang.minishops.service.ProductService;
import com.sang.minishops.service.imp.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final UserDetailService userDetailService;


    private final ProductService productService;

    private final ImageService imageService;

    private final ProductCsvDtoToProduct productCsvDtoToProduct;

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
            Product product = productService.getProductById(id);
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
    public String deleteProductById(@RequestParam("id") int id) {
        Product product = productService.getProductById(id);
        Set<Image> images = product.getImages();
        for (Image image : images) {
            imageService.deleteById(image.getId());
        }
        productService.deleteProduct(id);
        return "redirect:/listproduct";
    }

    @GetMapping("/home")
    public String showListProduct(Model model) {
        List<Product> listProduct = productService.getAllProduct();
        model.addAttribute("listproduct", listProduct);
        String currentUsername = userDetailService.getCurrentUsername();
        model.addAttribute("username", currentUsername);
        return "home";
    }

    @GetMapping("/uploadCsv")
    public String csvUploadFile() {
        return "upload-csv";
    }

    @PostMapping("/upload-csv")
    public String csvUploadFile(@RequestParam("csvFile") MultipartFile csvFile, Model model) {
        if (!csvFile.isEmpty()) {
            try {
                Reader reader = new InputStreamReader(csvFile.getInputStream());
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

                // Lấy thông tin về header
                Iterator<CSVRecord> iterator = records.iterator();
                if (iterator.hasNext()) {
                    CSVRecord headerRecord = iterator.next();
                    List<String> headers = new ArrayList<>();
                    for (String header : headerRecord) {
                        headers.add(header);
                    }
                }

                List<ProductCsvDto> productCsvDtos = new ArrayList<>();
                for (CSVRecord recorder : records) {
                    ProductCsvDto productCsvDto = new ProductCsvDto();
                    productCsvDto.setProductName(recorder.get(0));
                    productCsvDto.setProductDescription(recorder.get(1));
                    productCsvDto.setProductDiscountedPrince(Double.parseDouble(recorder.get(2)));
                    productCsvDto.setProductActualPrince(Double.parseDouble(recorder.get(3)));
                    productCsvDto.setImageUrl(recorder.get(4));
                    productCsvDtos.add(productCsvDto);
                }

                for (ProductCsvDto productCsvDto : productCsvDtos) {
                    Product product = productCsvDtoToProduct.convertProductCsvDtotoProduct(productCsvDto);
                    productService.saveProductCsv(product);
                }
                reader.close();
                model.addAttribute("successMessage", "CSV file uploaded and processed successfully");
            } catch (Exception e) {
                // Xử lý lỗi nếu có
                model.addAttribute("errorMessage", "Error uploading and processing CSV file: ");
            }
        } else {
            model.addAttribute("errorMessage", "Please select a CSV file to upload");
        }
        return "upload-csv";
    }
}