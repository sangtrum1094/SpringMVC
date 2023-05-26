package com.sang.minishops.service.imp;

import com.sang.minishops.entity.Role;
import com.sang.minishops.repository.RoleRepository;
import com.sang.minishops.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Role findById(Integer id) {
        Optional<Role> roles = roleRepository.findById(id);
        Role role =roles.get();
        return role;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
