package com.restful.socialmedia.service;

import com.restful.socialmedia.model.Post;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.repository.PostRepository;
import com.restful.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(User author, String title, String text, List<String> imagePath) {
        Post post = new Post();
        post.setAuthor(author);
        post.setTitle(title);
        post.setText(text);
        post.setImagePath(imagePath);

        return postRepository.save(post);
    }

    public void updatePost(Long postId, String newTitle, String newText, List<String> imagePath) {
        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new RuntimeException("Post not found")) ;
        post.setTitle(newTitle);
        post.setText(newText);
        post.setImagePath(imagePath);

        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getPostByAuthor(Long authorId, User authenticatedUser) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        return postRepository.findByAuthor(author);
    }
}
