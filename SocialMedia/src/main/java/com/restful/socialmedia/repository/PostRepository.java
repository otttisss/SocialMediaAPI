package com.restful.socialmedia.repository;

import com.restful.socialmedia.model.Post;
import com.restful.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthorIn(List<User> authors, Pageable pageable);
}
