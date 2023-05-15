package com.sang.minishops.service;

import com.sang.minishops.entity.Cart;
import com.sang.minishops.entity.Product;
import com.sang.minishops.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface CartService {

    void addToCart(Cart cart);
    List<Cart> findAllByUserId(int id);

    Product findByProductId(int id);

    Cart  findByUserIdAndProductId(int userId, int productId);
}
