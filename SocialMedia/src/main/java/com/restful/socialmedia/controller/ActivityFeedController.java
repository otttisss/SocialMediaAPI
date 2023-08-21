package com.restful.socialmedia.controller;

import com.restful.socialmedia.model.Post;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.service.ActivityFeedService;
import com.restful.socialmedia.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;


import java.util.List;

@RestController
@RequestMapping("/api/activity-feed")
public class ActivityFeedController {
    private final ActivityFeedService activityFeedService;
    private final UserService userService;
    public ActivityFeedController(ActivityFeedService activityFeedService, UserService userService) {
        this.activityFeedService = activityFeedService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getUserActivityFeed(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.getUserByName(userDetails.getUsername());
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

            List<Post> activityFeed = activityFeedService.getActivityFeed(user, pageable);

            return new ResponseEntity<>(activityFeed, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
