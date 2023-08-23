package com.restful.socialmedia.repository;

import com.restful.socialmedia.model.Friends;
import com.restful.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<Friends, Long> {
    List<Friends> findByReceiver(User receiver);
    List<Friends> findBySenderAndReceiver(User sender, User receiver);
}
