package com.facebook.model;

import java.util.Date;

public class Post {
    private int postId;
    private int accountId;
    private String postText;
    private String mediaAttachment;
    private String privacySetting;
    private Date submittedDate;

    public Post() {}
    // Constructors, Getters, and Setters
    public Post(int postId, int accountId, String postText, String mediaAttachment, String privacySetting, Date submittedDate) {
        this.postId = postId;
        this.accountId = accountId;
        this.postText = postText;
        this.mediaAttachment = mediaAttachment;
        this.privacySetting = privacySetting;
        this.submittedDate = submittedDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getMediaAttachment() {
        return mediaAttachment;
    }

    public void setMediaAttachment(String mediaAttachment) {
        this.mediaAttachment = mediaAttachment;
    }

    public String getPrivacySetting() {
        return privacySetting;
    }

    public void setPrivacySetting(String privacySetting) {
        this.privacySetting = privacySetting;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", accountId=" + accountId + ", postText=" + postText + ", mediaAttachment="
				+ mediaAttachment + ", privacySetting=" + privacySetting + ", submittedDate=" + submittedDate + "]";
	}
    
    
}
