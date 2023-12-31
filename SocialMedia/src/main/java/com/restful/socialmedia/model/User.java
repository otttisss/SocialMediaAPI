package com.restful.socialmedia.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Post> posts;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<Friends> receivedFriends;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private Set<Friends> sentFriends;

    @ManyToMany
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends = new HashSet<>();

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Friends> getReceivedFriendRequest() {
        return receivedFriends;
    }

    public void setReceivedFriendRequest(Set<Friends> receivedFriends) {
        this.receivedFriends = receivedFriends;
    }

    public Set<Friends> getSentFriendRequest() {
        return sentFriends;
    }

    public void setSentFriendRequest(Set<Friends> sentFriends) {
        this.sentFriends = sentFriends;
    }
}
