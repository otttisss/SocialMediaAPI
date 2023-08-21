package com.restful.socialmedia.controller;

import com.restful.socialmedia.model.Friends;
import com.restful.socialmedia.model.FriendsRequests;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.service.FriendRequestService;
import com.restful.socialmedia.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friend-request")
public class FriendRequestController {
    private final FriendRequestService friendRequestService;
//    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final UserService userService;

    public FriendRequestController(FriendRequestService friendRequestService, UserService userService) {
        this.friendRequestService = friendRequestService;
//        this.authenticatedUserProvider = authenticatedUserProvider;
        this.userService = userService;
    }

    @PostMapping("/send")
    public ResponseEntity<Friends> sendFriendRequest(@RequestBody FriendsRequests requests) {
//        User sender = authenticatedUserProvider.getUserFromAuthenticatedPrincipal();
//        User receiver = userService.getUserByName(requests.getUsername());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User sender = userService.getUserByName(userDetails.getUsername());
            User receiver = userService.getUserByName(userDetails.getUsername());
            Friends friends = friendRequestService.sendFriendRequest(sender, receiver);
            return new ResponseEntity<>(friends, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/accept/{requestId}")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable Long requestId) {
        friendRequestService.acceptFriendRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reject/{requestId}")
    public ResponseEntity<Void> rejectFriendRequest(@PathVariable Long requestId) {
        friendRequestService.rejectFriendRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
