package com.restful.socialmedia.controller;

import com.restful.socialmedia.model.Post;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.model.PostRequest;
import com.restful.socialmedia.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restful.socialmedia.config.AuthenticatedUserProvider;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public PostController(PostService postService, AuthenticatedUserProvider authenticatedUserProvider) {
        this.postService = postService;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        User author = authenticatedUserProvider.getUserFromAuthenticatedPrincipal();
        Post post = postService.createPost(author, request.getTitle(), request.getText());

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }


}
