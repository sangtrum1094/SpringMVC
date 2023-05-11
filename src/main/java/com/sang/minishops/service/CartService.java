package com.sang.minishops.service;

import com.sang.minishops.entity.Product;
import com.sang.minishops.entity.User;

import java.util.List;

public interface CartService {
    List<Product> getProductList(User user);
}
