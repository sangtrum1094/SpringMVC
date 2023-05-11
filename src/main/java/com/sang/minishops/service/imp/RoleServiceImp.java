package com.sang.minishops.service.imp;

import com.sang.minishops.entity.Role;
import com.sang.minishops.repository.RoleRepository;
import com.sang.minishops.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository roleRepository;


    @Override
    public Optional<Role> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
