package com.restful.socialmedia.model;

import java.util.List;

public class PostRequest {
    private String title;
    private String text;
    private List<String> imagePath;

    public PostRequest(String title, String text, List<String> imagePath) {
        this.title = title;
        this.text = text;
        this.imagePath = imagePath;
    }

    public List<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(List<String> imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
