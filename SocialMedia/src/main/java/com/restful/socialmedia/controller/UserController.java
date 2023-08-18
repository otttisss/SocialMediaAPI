package com.restful.socialmedia.controller;

import com.restful.socialmedia.model.RegistrationRequest;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegistrationRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getEmail(), request.getPassword());

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
