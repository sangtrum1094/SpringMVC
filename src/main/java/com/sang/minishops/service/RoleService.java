package com.sang.minishops.service;

import com.sang.minishops.entity.Role;

public interface RoleService {
    Role findById(Integer id);

    void save(Role role1);
}
