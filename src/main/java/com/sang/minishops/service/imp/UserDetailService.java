package com.sang.minishops.service.imp;

import com.sang.minishops.entity.User;
import com.sang.minishops.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * The type User detail devices.
 */
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    /**
     * The User repository.
     */

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails userDetails ) {
            return userDetails.getUsername();
        }
        return null;
    }

}
