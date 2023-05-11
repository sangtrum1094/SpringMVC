package com.sang.minishops.service.imp;

import com.sang.minishops.entity.Role;
import com.sang.minishops.entity.User;
import com.sang.minishops.repository.UserRepository;
import com.sang.minishops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User service imp.
 */
@Service
public class UserServiceImp implements UserService {

    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByUserName(String name) {
        User user = userRepository.findUserByUsername(name);
        return user;
    }
    @Override
    public void saveUser(User user) {
        User user1 = userRepository.save(user);
    }


}
