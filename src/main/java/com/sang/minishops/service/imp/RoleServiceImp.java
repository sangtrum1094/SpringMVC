package com.sang.minishops.service.imp;

import com.sang.minishops.entity.Role;
import com.sang.minishops.repository.RoleRepository;
import com.sang.minishops.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Role not found"));
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
