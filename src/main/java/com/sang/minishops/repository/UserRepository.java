package com.sang.minishops.repository;

import com.sang.minishops.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Find user by username user.
     *
     * @param username the username
     * @return the user
     */
    User findUserByUsername(String username);

}
