package com.sang.minishops.config;

import com.google.gson.Gson;
import com.sang.minishops.dto.CartItemDto;
import com.sang.minishops.entity.Cart;
import com.sang.minishops.entity.Product;
import com.sang.minishops.entity.User;
import com.sang.minishops.service.CartService;
import com.sang.minishops.service.ProductService;
import com.sang.minishops.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private CartService cartService;
    //
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public CustomAuthenticationSuccessHandler() {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Cookie[] cookies = request.getCookies();
        Cookie cartCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cart")) {
                cartCookie = cookie;
                break;
            }
        }
        if (cartCookie != null) {
            String cartValue = cartCookie.getValue();
            Gson gson = new Gson();
            String decodedCartValue = URLDecoder.decode(cartValue, "UTF-8");
            CartItemDto[] cartItemDto = gson.fromJson(decodedCartValue, CartItemDto[].class);

            for (CartItemDto item : cartItemDto) {
                int id = item.getId();
                int quantity = item.getQuantity();
                String username = auth.getName();
                User user = userService.findUserByUserName(username);
                Product product = productService.GetProductById(id);

                Cart cart = new Cart();
                cart.setUser(user);
                cart.setQuantity(quantity);
                cart.setCreatedDate(new Date());
                cart.setProduct(product);
                cartService.addToCart(cart);
            }
            Cookie cookie = new Cookie("cart", "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect(request.getContextPath() + "/cart");
        }
        response.sendRedirect(request.getContextPath() + "/home");
    }
}

