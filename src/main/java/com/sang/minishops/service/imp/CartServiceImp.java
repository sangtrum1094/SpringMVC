package com.sang.minishops.service.imp;

import com.sang.minishops.entity.Card;
import com.sang.minishops.entity.Product;
import com.sang.minishops.entity.User;
import com.sang.minishops.repository.CardRepository;
import com.sang.minishops.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    CardRepository cardRepository;


    @Override
    public List<Product> getProductList(User user) {
        Card card =user.getCard();
        List<Product> productList= new ArrayList<>();
        for(Product product : card.getProducts()){
            productList.add(product);
        }
        return productList;
    }
}
