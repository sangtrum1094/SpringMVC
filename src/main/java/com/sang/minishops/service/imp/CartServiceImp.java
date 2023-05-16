package com.sang.minishops.service.imp;

import com.sang.minishops.entity.Cart;
import com.sang.minishops.entity.Product;
import com.sang.minishops.repository.CartRepository;
import com.sang.minishops.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    CartRepository cartRepository;


    @Override
    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> findAllByUserId(int id) {
        return cartRepository.findAllByUserId(id);
    }

    @Override
    public Product findByProductId(int id) {
        return cartRepository.findByProductId(id);
    }

    @Override
    public Cart findByUserIdAndProductId(int userId, int productId) {
        Optional<Cart> optionalCart = cartRepository.findByUserIdAndProductId(userId, productId);
        if (optionalCart.isEmpty()) {
            return null;
        }
        return optionalCart.get();
    }

    @Override
    public void deleteCartById(int id) {
        cartRepository.deleteCartById(id);
    }

}