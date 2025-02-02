package com.gofit.service;

import com.gofit.entity.CustomUserDetails;
import com.gofit.entity.User;
import com.gofit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.findByUsername(username);
        } catch (ResourceNotFoundException r) {
            throw new UsernameNotFoundException("Your login details were incorrect.");
        }
        return new CustomUserDetails(user);
    }
}
