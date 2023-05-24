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
    public Optional<Role> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
