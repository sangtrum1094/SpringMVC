package com.sang.minishops.service;
import com.sang.minishops.entity.Cart;
import com.sang.minishops.entity.Product;
import java.util.List;


public interface CartService {

    void addToCart(Cart cart);

    List<Cart> findAllByUserId(int id);

    Product findByProductId(int id);

    Cart findByUserIdAndProductId(int userId, int productId);

    void deleteCartById(int id);
}
