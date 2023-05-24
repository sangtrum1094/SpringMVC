package com.sang.minishops.controller;

import com.sang.minishops.dto.CartItemDto;
import com.sang.minishops.entity.Cart;
import com.sang.minishops.entity.Product;
import com.sang.minishops.entity.User;
import com.sang.minishops.service.CartService;
import com.sang.minishops.service.ProductService;
import com.sang.minishops.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;

    private final CartService cartService;

    private final UserService userService;

    @PostMapping("/addcart")
    public String addToCart(@ModelAttribute("cartItemDto") CartItemDto cartItemDto, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUserByUserName(username);
        int userId = user.getId();
        int productId = cartItemDto.getId();

        Cart cart = cartService.findByUserIdAndProductId(userId, productId);
        if (cart == null) {
            Cart cartNew = new Cart();
            Product product = productService.GetProductById(productId);
            cartNew.setProduct(product);
            cartNew.setUser(user);
            cartNew.setQuantity(cartItemDto.getQuantity());
            cartNew.setCreatedDate(new Date());
            cartService.addToCart(cartNew);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showAllCart(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByUserName(name);
        Optional<User> optionalUser = Optional.ofNullable(user);
        int id = optionalUser.map(User::getId).orElse(-1);
        List<Cart> carts = cartService.findAllByUserId(id);
        model.addAttribute("carts", carts);
        return "cart";
    }

    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteCartById(@PathVariable("id") int id) {
        cartService.deleteCartById(id);
        return "redirect:/cart";
    }
}





















