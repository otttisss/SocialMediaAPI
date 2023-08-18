package com.restful.socialmedia.controller;

import com.restful.socialmedia.config.AuthenticatedUserProvider;
import com.restful.socialmedia.model.FriendRequest;
import com.restful.socialmedia.model.FriendsRequests;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.service.FriendRequestService;
import com.restful.socialmedia.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/friend-request")
public class FriendRequestController {
    private final FriendRequestService friendRequestService;
    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final UserService userService;

    public FriendRequestController(FriendRequestService friendRequestService, AuthenticatedUserProvider authenticatedUserProvider,
                                   UserService userService) {
        this.friendRequestService = friendRequestService;
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.userService = userService;
    }

    @PostMapping("/send")
    public ResponseEntity<FriendRequest> sendFriendRequest(@RequestBody FriendsRequests requests) {
        User sender = authenticatedUserProvider.getUserFromAuthenticatedPrincipal();
        User receiver = userService.getUserByName(requests.getUsername());
        FriendRequest friendRequest = friendRequestService.sendFriendRequest(sender, receiver);

        return new ResponseEntity<>(friendRequest, HttpStatus.CREATED);
    }
}
