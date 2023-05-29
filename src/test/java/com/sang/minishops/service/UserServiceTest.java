package com.sang.minishops.service;

import com.sang.minishops.entity.User;
import com.sang.minishops.repository.UserRepository;
import com.sang.minishops.service.imp.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImp(userRepository);
    }

    @Test
    public void testFindUserByUserName() {
        String username = "sang";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        Mockito.when(userRepository.findUserByUsername(username)).thenReturn(expectedUser);


        User actualUser = userService.findUserByUserName(username);


        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testSaveUser() {

        User user = new User();


        userService.saveUser(user);


        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void testFindUserById() {

        int userId = 1;
        User expectedUser = new User();
        expectedUser.setId(userId);
        Mockito.when(userRepository.findUserById(userId)).thenReturn(expectedUser);


        User actualUser = userService.findUserById(userId);


        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }
}