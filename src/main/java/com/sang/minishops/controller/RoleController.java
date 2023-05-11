package com.sang.minishops.controller;

import com.sang.minishops.entity.Role;
import com.sang.minishops.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/addrole")
    public String AddRolePage(){
        return "addrole";
    }
   @PostMapping("/addrole")
    public String AddnewRole(@RequestParam("name") String name){
       Role role= new Role();
       role.setName(name);
       roleService.save(role);
       return "listproduct";
   }

}
