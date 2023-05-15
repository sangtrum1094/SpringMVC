package com.sang.minishops.repository;

import com.sang.minishops.entity.Cart;
import com.sang.minishops.entity.Product;
import com.sang.minishops.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUser(User user);

     List<Cart> findAllByUserId(int id);

     Product findByProductId(int id);

    Optional<Cart> findByUserIdAndProductId(int userId,int productId);
}
