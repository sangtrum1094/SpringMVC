package com.sang.minishops.service;

import com.sang.minishops.entity.Role;
import com.sang.minishops.entity.User;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Find user by user name user.
     *
     * @param name the name
     * @return the user
     */
    User findUserByUserName(String name);

    /**
     * Save user.
     *
     * @param user the user
     */
    void saveUser(User user);

    User findUserById(int id);
}
