package com.sang.minishops.controller;

import com.sang.minishops.entity.Role;
import com.sang.minishops.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/addrole")
    public String addRolePage() {
        return "addrole";
    }

    @PostMapping("/addrole")
    public String addnewRole(@RequestParam("name") String name) {
        Role role = new Role();
        role.setName(name);
        roleService.save(role);
        return "listproduct";
    }

}
