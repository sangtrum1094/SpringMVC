package com.sang.minishops.service;

import com.sang.minishops.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(Integer id);
    void save(Role role1);
}
