package com.sang.minishops.repository;

import com.sang.minishops.entity.Card;
import com.sang.minishops.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findByUser(User user);
}
