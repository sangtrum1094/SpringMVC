package com.sang.minishops.service.imp;

import com.sang.minishops.entity.User;
import com.sang.minishops.repository.UserRepository;
import com.sang.minishops.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type User service imp.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    /**
     * The User repository.
     */
    private final UserRepository userRepository;


    @Override
    public User findUserByUserName(String name) {
        return userRepository.findUserByUsername(name);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }


}
