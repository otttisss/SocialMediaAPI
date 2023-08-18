package com.restful.socialmedia.controller;

import com.restful.socialmedia.model.Post;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.model.PostRequest;
import com.restful.socialmedia.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restful.socialmedia.config.AuthenticatedUserProvider;

import java.util.List;

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

    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostRequest request) {
        postService.updatePost(postId, request.getTitle(), request.getText());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Post>> getPostsByAuthor(@PathVariable Long authorId) {
        User author = authenticatedUserProvider.getUserFromAuthenticatedPrincipal();
        List<Post> posts = postService.getPostByAuthor(authorId, author);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
