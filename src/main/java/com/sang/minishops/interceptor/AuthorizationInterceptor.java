package com.sang.minishops.interceptor;

import com.sang.minishops.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collection;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    final UserService userService;

    public AuthorizationInterceptor(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            // Kiểm tra quyền của người dùng
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                // Người dùng không có quyền truy cập
                response.sendRedirect("/access-denied");
                return false;
            }
            // Người dùng có quyền truy cập, cho phép tiếp tục xử lý request
            return true;
        }
        return false;
    }
}
