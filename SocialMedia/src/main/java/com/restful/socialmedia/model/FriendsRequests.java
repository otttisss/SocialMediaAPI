package com.restful.socialmedia.model;

public class FriendsRequests {
    private String username;

    public FriendsRequests(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
