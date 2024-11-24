package com.facebook.model;

public class Dislike {
    private User user;
    private Post post;

    
    public Dislike(User user, Post post) {
        this.user = user;
        this.post = post;
    }

   
    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }
}
