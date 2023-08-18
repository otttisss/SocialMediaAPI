package com.restful.socialmedia.service;

import com.restful.socialmedia.model.FriendRequest;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;

    public FriendRequestService(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }

    public FriendRequest sendFriendRequest(User sender, User receiver) {
        List<FriendRequest> existingRequest = friendRequestRepository.findBySenderAndReceiver(sender, receiver);

        if (!existingRequest.isEmpty())
            throw new RuntimeException("Friend request already sent");

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);

        return friendRequestRepository.save(friendRequest);
    }

    public void acceptFriendRequest(Long requestId) {
        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));

        // TODO: add both users as friends

        friendRequestRepository.delete(request);
    }

    public void rejectFriendRequest(Long requestId) {
        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));

        friendRequestRepository.delete(request);
    }
}
