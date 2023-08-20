package com.restful.socialmedia.config;

import com.restful.socialmedia.model.User;
import com.restful.socialmedia.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserProvider {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public AuthenticatedUserProvider(UserRepository userRepository, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    public User getUserFromAuthenticatedPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//            return userRepository.findByUsername(userDetails.getUsername()).orElse(null);
            return (User) userDetailsService.loadUserByUsername(userDetails.getUsername());
        }
        return null;
    }
}
