package com.restful.socialmedia.service;

import com.restful.socialmedia.model.Post;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(User author, String title, String text) {
        Post post = new Post();
        post.setAuthor(author);
        post.setTitle(title);
        post.setText(text);

        return postRepository.save(post);
    }

    public void updatePost(Long postId, String newTitle, String newText) {
        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new RuntimeException("Post not found")) ;
        post.setTitle(newTitle);
        post.setText(newText);

        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getPostByAuthor(User author) {
        return postRepository.findByAuthor(author);
    }
}
