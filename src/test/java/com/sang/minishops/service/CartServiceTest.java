package com.sang.minishops.service;

import com.sang.minishops.entity.Cart;
import com.sang.minishops.entity.Product;
import com.sang.minishops.entity.User;
import com.sang.minishops.repository.CartRepository;
import com.sang.minishops.service.imp.CartServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    @Mock
    CartRepository cartRepository;

    CartService cartService;

    @BeforeEach
    void setUp() {
        cartRepository = mock(CartRepository.class);
        cartService = new CartServiceImp(cartRepository);
    }

    @Test
    void addToCart() {
        Cart cart = new Cart();
        cart.setProduct(new Product());
        cart.setQuantity(1);
        cart.setUser(new User());

        cartService.addToCart(cart);

        verify(cartRepository).save(cart);
    }


    @Test
    void findAllByUserId() {
        int userId = 1;
        List<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart(12));
        cartList.add(new Cart());
        when(cartRepository.findAllByUserId(userId)).thenReturn(cartList);

        // Gọi phương thức cần kiểm tra
        List<Cart> result = cartService.findAllByUserId(userId);

        // Kiểm tra kết quả
        assertEquals(2, result.size());
        assertEquals(12, result.get(0).getQuantity());
    }

    @Test
    void findByProductId() {
        int productId = 1;
        Product product = new Product();
        when(cartRepository.findByProductId(productId)).thenReturn(product);
        Product result = cartService.findByProductId(productId);

        assertEquals(product, result);
    }

    @Test
    void findByUserIdAndProductId() {
        int userId = 1;
        int productId = 1;
        Optional<Cart> optionalCart = Optional.of(new Cart());
        when(cartRepository.findByUserIdAndProductId(userId, productId)).thenReturn(optionalCart);

        // Gọi phương thức cần kiểm tra
        Cart result = cartService.findByUserIdAndProductId(userId, productId);

        assertEquals(optionalCart.get(), result);


    }

    @Test
    void deleteCartById() {
        int cartId = 1;

        // Gọi phương thức cần kiểm tra
        cartService.deleteCartById(cartId);

        verify(cartRepository).deleteCartById(cartId);

    }
}