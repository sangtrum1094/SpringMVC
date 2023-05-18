package com.sang.minishops.controller;

import com.sang.minishops.entity.Role;
import com.sang.minishops.entity.User;
import com.sang.minishops.repository.RoleRepository;
import com.sang.minishops.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * The type User controller.
 */
@Controller
public class UserController {
    /**
     * The User service imp.
     */
    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    RoleRepository roleRepository;


    @GetMapping("/adduser")
    public String adduser() {
        return "adduser";
    }

    @PostMapping("/adduser")
    public String addUSer(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);

        userServiceImp.saveUser(user);
        return "adduser";
    }

    ;

    /**
     * Login page string.
     *
     * @return the string
     */
    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    /**
     * Login page string.
     *
     * @param username1 the username
     * @return the string
     */

    /**
     * Admin page string.
     *
     * @return the string
     */
    @GetMapping("/admin")
    public String AdminPage() {
        return "admin";
    }


    @GetMapping("/user")
    public String UserPage() {
        return "user";
    }


    /**
     * Add role page string.
     *
     * @return the string
     */
    @GetMapping("/adduserrole")
    public String AddRolePage() {
        return "adduserrole";
    }

    /**
     * Add rolepage string.
     *
     * @param username the username
     * @param id       the id
     * @return the string
     */
    @PostMapping("/adduserrole")
    public String AddRolepage(@RequestParam String username, @RequestParam int id) {
        User user = userServiceImp.findUserByUserName(username);
        Optional<Role> roles = roleRepository.findById(id);
        if (roles.isPresent()) {
            Role role1 = roles.get();
            roleRepository.save(role1);
            user.addRole(role1);
            userServiceImp.saveUser(user);
            return "listproduct";
        }
        return "adduserrole";
    }

}
