package com.sang.minishops.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sang.minishops.dto.CartItemDto;
import com.sang.minishops.entity.Cart;
import com.sang.minishops.entity.Product;
import com.sang.minishops.entity.User;
import com.sang.minishops.service.CartService;
import com.sang.minishops.service.ProductService;
import com.sang.minishops.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;


@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;


    @Autowired
    UserService userService;

    @PostMapping("/addcart")
    public String addToCart(@ModelAttribute("cartItemDto") CartItemDto cartItemDto, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUserByUserName(username);
        int userId =user.getId();
        int productId = cartItemDto.getId();

        Cart cart = cartService.findByUserIdAndProductId(userId,productId);
        if(cart==null){
            Cart cartNew = new Cart();
            Product product = productService.GetProductById(productId);
            cartNew.setProduct(product);
            cartNew.setUser(user);
            cartNew.setQuantity(cartItemDto.getQuantity());
            cartNew.setCreatedDate(new Date());
            cartService.addToCart(cartNew);
        }
           return  null;
        }

    @GetMapping("/cart")
    public String ShowAllCart(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name =auth.getName();
        User user = userService.findUserByUserName(name);
        int id = user.getId();
        List<Cart> carts=cartService.findAllByUserId(id);
        model.addAttribute("carts",carts);
        return "cart";

    }
}





















