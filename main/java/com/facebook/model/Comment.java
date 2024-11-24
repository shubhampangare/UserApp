package com.facebook.model;

public class Comment {
    private int commentId;
    private User user;
    private Post post;
    private String commentText;
    private String submitDate;

  
    public Comment(User user, Post post, String commentText, String submitDate) {
        this.user = user;
        this.post = post;
        this.commentText = commentText;
        this.submitDate = submitDate;
    }

    
    public int getCommentId() {
        return commentId;
    }

    public String getCommentText() {
        return commentText;
    }
}