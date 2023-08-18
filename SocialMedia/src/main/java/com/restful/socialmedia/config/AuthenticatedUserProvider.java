package com.restful.socialmedia.config;

import com.restful.socialmedia.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserProvider {
    public User getUserFromAuthenticatedPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // Здесь можно получить пользователя из базы данных по username из userDetails
            // Вернуть соответствующего пользователя или null, если не найден
        }
        return null;
    }
}
