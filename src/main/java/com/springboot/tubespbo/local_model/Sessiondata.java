package com.springboot.tubespbo.local_model;

import com.springboot.tubespbo.model.User;

public class Sessiondata {
    private User user;
    private String role;
    public Sessiondata(User user, String role) {
        this.user = user;
        this.role = role;
    }
    public User getUser() {
        return user;
    }
    public String getRole() {
        return role;
    }
    
}
