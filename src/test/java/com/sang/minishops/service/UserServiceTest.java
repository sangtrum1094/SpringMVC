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
        userService = new UserServiceImp(userRepository); // Khởi tạo đối tượng userService với userRepository đã khởi tạo
    }

    @Test
    public void testFindUserByUserName() {
        // Arrange
        String username = "testuser";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        Mockito.when(userRepository.findUserByUsername(username)).thenReturn(expectedUser);

        // Act
        User actualUser = userService.findUserByUserName(username);

        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

//    @Test
//    public void testSaveUser() {
//        // Arrange
//        User user = new User();
//
//        // Act
//        userService.saveUser(user);
//
//        // Assert
//        Mockito.verify(userRepository).save(user);
//    }

//    @Test
//    public void testFindUserById() {
//        // Arrange
//        int userId = 1;
//        User expectedUser = new User();
//        expectedUser.setId(userId);
//        Mockito.when(userRepository.findUserById(userId)).thenReturn(expectedUser);
//
//        // Act
//        User actualUser = userService.findUserById(userId);
//
//        // Assert
//        assertNotNull(actualUser);
//        assertEquals(expectedUser, actualUser);
//    }
}