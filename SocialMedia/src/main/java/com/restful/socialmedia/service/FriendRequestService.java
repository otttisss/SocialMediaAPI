package com.restful.socialmedia.service;

import com.restful.socialmedia.model.Friends;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.repository.FriendRequestRepository;
import com.restful.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    public Friends sendFriendRequest(User sender, User receiver) {
        List<Friends> existingRequest = friendRequestRepository.findBySenderAndReceiver(sender, receiver);

        if (!existingRequest.isEmpty())
            throw new RuntimeException("Friend request already sent");

        Friends friends = new Friends();
        friends.setSender(sender);
        friends.setReceiver(receiver);

        return friendRequestRepository.save(friends);
    }

    public void acceptFriendRequest(Long requestId) {
        Friends request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));

        User sender = request.getSender();
        User receiver = request.getReceiver();

        sender.getFriends().add(receiver);
        receiver.getFriends().add(sender);

        friendRequestRepository.delete(request);

        userRepository.save(sender);
        userRepository.save(receiver);
    }

    public void rejectFriendRequest(Long requestId) {
        Friends request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));

        friendRequestRepository.delete(request);
    }
}
