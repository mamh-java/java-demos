package com.atguigu.mvcapp.domain;

import java.util.List;

public class Permission {
    private String username;

    private List<Authority> authorities;


    public Permission() {
    }

    public Permission(String username, List<Authority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }


    @Override
    public String toString() {
        return "Permission{" +
                "username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
