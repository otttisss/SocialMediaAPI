package com.restful.socialmedia.controller;

import com.restful.socialmedia.config.AuthenticatedUserProvider;
import com.restful.socialmedia.model.Post;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.service.ActivityFeedService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/activity-feed")
public class ActivityFeedController {
    private final ActivityFeedService activityFeedService;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public ActivityFeedController(ActivityFeedService activityFeedService, AuthenticatedUserProvider authenticatedUserProvider) {
        this.activityFeedService = activityFeedService;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getUserActivityFeed(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        User user = authenticatedUserProvider.getUserFromAuthenticatedPrincipal();
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by("createdAt").descending());

        List<Post> activityFeed = activityFeedService.getActivityFeed(user, pageable);

        return new ResponseEntity<>(activityFeed, HttpStatus.OK);
    }
}
