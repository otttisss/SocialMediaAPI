package com.restful.socialmedia.service;

import com.restful.socialmedia.model.Post;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityFeedService {
    private final PostRepository postRepository;

    public ActivityFeedService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getActivityFeed(User user, Pageable pageable) {
        List<User> friends = new ArrayList<>();
        friends.add(user);

        return postRepository.findByAuthorIn(friends, pageable);
    }
}
