package com.facebook.model;

public class Like {
    private User user;
    private Post post;

   
    public Like(User user, Post post) {
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
