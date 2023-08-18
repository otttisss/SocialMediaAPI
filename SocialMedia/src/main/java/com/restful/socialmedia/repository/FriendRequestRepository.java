package com.restful.socialmedia.repository;

import com.restful.socialmedia.model.FriendRequest;
import com.restful.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findByReceiver(User receiver);
    List<FriendRequest> findBySenderAndReceiver(User sender, User receiver);
}
