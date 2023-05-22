package com.sang.minishops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class InterceptorAdminController {
    @GetMapping("/interceptor/admin")
    public String adminPage() {
        return "interceptor-admin";
    }
    @GetMapping("/access-denied")
    public String noAuthentication(){
        return "access-denied";
    }
}
