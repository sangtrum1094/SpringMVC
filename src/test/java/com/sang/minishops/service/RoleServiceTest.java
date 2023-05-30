package com.sang.minishops.service;

import com.sang.minishops.entity.Role;
import com.sang.minishops.repository.RoleRepository;
import com.sang.minishops.service.imp.RoleServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
    @Mock
    private RoleRepository roleRepository;
    private  RoleService roleService;

    @BeforeEach
     void setup(){
        roleRepository = mock(RoleRepository.class);
        roleService = new RoleServiceImp(roleRepository);
    }
    @Test
      void findById() {
        int roleId=1;
        Role expectedRole = new Role();
        expectedRole.setId(roleId);
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(expectedRole));

        Role  actualRole = roleService.findById(roleId);

        assertNotNull(actualRole);
        assertEquals(expectedRole,actualRole);
    }

    @Test
     void save() {
        Role role = new Role();

        roleService.save(role);

        Mockito.verify(roleRepository).save(role);
    }
}