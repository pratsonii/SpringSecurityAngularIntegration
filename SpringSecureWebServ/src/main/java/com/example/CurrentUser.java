package com.example;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUserName(), user.getPassword(),  AuthorityUtils.createAuthorityList(user.getRole()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getRole() {
        return "ROLE_USER";
    }

}