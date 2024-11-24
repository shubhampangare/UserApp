package com.facebook.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int groupId;
    private String groupName;
    private String description;
    private String groupType;
    
    private List<User> members = new ArrayList<>();
    private List<User> admins = new ArrayList<>();
    
   
    public Group(int groupId, String groupName, String description, String groupType) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.groupType = groupType;
    }

   
    public int getGroupId() {
        return groupId;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public void addAdmin(User user) {
        admins.add(user);
    }

    public void removeAdmin(User user) {
        admins.remove(user);
    }

    public List<User> getMembers() {
        return members;
    }

    public List<User> getAdmins() {
        return admins;
    }
}