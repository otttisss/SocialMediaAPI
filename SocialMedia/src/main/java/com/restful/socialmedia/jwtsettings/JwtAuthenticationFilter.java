package com.restful.socialmedia.jwtsettings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.socialmedia.model.LoginForm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginForm loginForm;
        try {
            loginForm = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Failed to parse authentication request");
        }
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String token = jwtUtils.generateJwtToken(userDetails);
        response.addHeader("Authorization", token);
    }
}
